package top.lzmvlog.authority.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lzmvlog.authority.model.Menu;
import top.lzmvlog.authority.service.MenuService;
import top.lzmvlog.authority.util.R;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2020年12月17日 14:48
 * @Description:
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    public MenuService menuService;

    /**
     * 编辑菜单
     *
     * @param menu 菜单信息
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("edit")
    public R save(@RequestBody Menu menu) {
        if (StringUtils.isEmpty(menu.getId())) {
            menuService.save(menu);
        } else {
            menuService.update(menu);
        }
        return R.ok();
    }

    /**
     * 查询菜单
     *
     * @param menu 菜单信息
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("selectList")
    public R selectList(Menu menu) {
        return R.ok(menuService.selectList(menu));
    }

    /**
     * 删除菜单
     *
     * @param id 菜单id
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("delete")
    public R delete(String id) {
        menuService.delete(id);
        return R.ok();
    }

}
