package top.lzmvlog.authority.service;

import top.lzmvlog.authority.model.Menu;
import top.lzmvlog.authority.model.vo.MenuTree;

import java.util.List;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2020年12月17日 11:44
 * @Description:
 */
public interface MenuService {

    /**
     * 新增菜单
     *
     * @param menu 菜单信息
     * @return
     */
    Integer save(Menu menu);

    /**
     * 更新菜单
     *
     * @param menu 菜单信息
     * @return
     */
    Integer update(Menu menu);

    /**
     * 删除菜单
     *
     * @param id 菜单id
     * @return
     */
    Integer delete(String id);

    /**
     * 分页查询菜单
     *
     * @param menu 菜单信息
     * @return
     */
    List<MenuTree> selectList(Menu menu);
}
