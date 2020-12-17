package top.lzmvlog.authority.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.lzmvlog.authority.model.Purview;
import top.lzmvlog.authority.service.PurviewService;
import top.lzmvlog.authority.util.R;

/**
 * @author ShaoJie
 * @Date 2020年07月30 11:43
 * @Description: 权限管理
 */
@RestController
@RequestMapping("purview")
public class PurviewController {

    @Autowired
    PurviewService purviewService;

    /**
     * 保存权限信息
     *
     * @param purview 权限对象
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("save")
    public R save(@RequestBody Purview purview) {
        purview.setRole("ROLE_" + purview.getRole());
        purviewService.insert(purview);
        return R.ok();
    }

    /**
     * 查询权限集合
     *
     * @param purview 权限对象
     * @param page    分页对象
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("selectPage")
    public R selectPage(Purview purview, Page page) {
        return R.ok(purviewService.selectPage(page, purview));
    }

    /**
     * 删除权限
     *
     * @param id 权限对象id
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("delete")
    public R deletePurview(String id) {
        purviewService.deletePurview(id);
        return R.ok();
    }

    /**
     * 更新权限
     *
     * @param purview 权限对象
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("update")
    public R updatePurview(@RequestBody Purview purview) {
        purviewService.update(purview);
        return R.ok();
    }

}
