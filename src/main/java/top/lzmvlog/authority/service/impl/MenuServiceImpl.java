package top.lzmvlog.authority.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.lzmvlog.authority.dao.MenuMapper;
import top.lzmvlog.authority.model.Menu;
import top.lzmvlog.authority.model.vo.MenuTree;
import top.lzmvlog.authority.service.MenuService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2020年12月17日 11:44
 * @Description:
 */
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Autowired
    public MenuMapper menuMapper;

    /**
     * 新增菜单
     *
     * @param menu 菜单信息
     * @return
     */
    @Override
    public Integer save(Menu menu) {
        return menuMapper.insert(menu);
    }

    /**
     * 更新菜单
     *
     * @param menu 菜单信息
     * @return
     */
    @Override
    public Integer update(Menu menu) {
        return menuMapper.updateById(menu);
    }

    /**
     * 删除菜单
     *
     * @param id 菜单id
     * @return
     */
    @Override
    public Integer delete(String id) {
        return menuMapper.deleteById(id);
    }

    /**
     * 分页查询菜单
     *
     * @param menu 菜单信息
     * @return TODO:当前菜单适应层级不完善，只能适应双层层级
     */
    @Override
    public List<MenuTree> selectList(Menu menu) {
        List<Menu> menus = menuMapper.selectList(Wrappers.<Menu>lambdaQuery()
                .eq(!StringUtils.isEmpty(menu.getMenuName()), Menu::getMenuName, menu.getMenuName()));

        List<MenuTree> menuTreeList = menus
                .stream()
                .filter(m -> StringUtils.isEmpty(m.getParentId()))
                .map(this::menuTree)
                .collect(Collectors.toList());

        menuTreeList.forEach(menuTree -> {
            List<MenuTree> collect = menus.stream()
                    .filter(m -> m.getParentId().equals(menuTree.getId()))
                    .map(this::menuTree)
                    .collect(Collectors.toList());
            menuTree.setChildren(collect);
        });

        return menuTreeList;
    }

    /**
     * 将菜单转成菜单树
     *
     * @param menu
     * @return
     */
    private MenuTree menuTree(Menu menu) {
        MenuTree menuTree = new MenuTree();
        BeanUtils.copyProperties(menu, menuTree);
        return menuTree;
    }
}
