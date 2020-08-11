package top.lzmvlog.authority.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lzmvlog.authority.model.Purview;
import top.lzmvlog.authority.service.PurviewService;
import top.lzmvlog.authority.util.data.R;

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
    public R save(@NotNull Purview purview) {
        purview.setRole("ROLE_" + purview.getRole());
        purviewService.insert(purview);
        return new R(HttpStatus.HTTP_OK, "添加成功");
    }

    /**
     * 查询权限集合
     *
     * @param purview 权限对象
     * @param page    分页对象
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("selectList")
    public R selectList(@NotNull Purview purview, Page page) {
        return new R(HttpStatus.HTTP_OK, purviewService.selectList(page, purview));
    }

    /**
     * 删除权限
     *
     * @param purview 权限对象
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("delete")
    public R deletePurview(@NotNull Purview purview) {
        purviewService.deletePurview(purview);
        return new R(HttpStatus.HTTP_OK, "删除权限信息成功");
    }

    /**
     * 更新权限
     *
     * @param purview 权限对象
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("updata")
    public R updatePurview(@NotNull Purview purview) {
        purviewService.updata(purview);
        return new R(HttpStatus.HTTP_OK, "更新权限信息成功");
    }

}
