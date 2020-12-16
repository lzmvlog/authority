package top.lzmvlog.authority.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.lzmvlog.authority.model.Authority;
import top.lzmvlog.authority.service.AuthorityService;
import top.lzmvlog.authority.util.data.R;

/**
 * @author ShaoJie
 * @Date 2020年07月30 17:50
 * @Description: 用户权限管理
 */
@RestController
@RequestMapping("authority")
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;

    /**
     * 新增用户权限
     *
     * @param authority 用户权限对象
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("save")
    public R save(@RequestBody Authority authority) {
        authorityService.save(authority);
        return new R(HttpStatus.HTTP_OK, "新增成功");
    }

    /**
     * 根据用户信息查询用户权限
     *
     * @param authority 权限对象
     * @param page      分页信息
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("selectPage")
    public R selectPage(Authority authority, Page page) {
        return new R(HttpStatus.HTTP_OK, authorityService.selectPage(page, authority));
    }

    /**
     * 取消用户的权限
     *
     * @param id       权限id
     * @param memberId 用户id
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("delete")
    public R deleteAuth(String id, String memberId) {
        authorityService.deleteAuth(id, memberId);
        return new R(HttpStatus.HTTP_OK, "取消权限成功");
    }

    /**
     * 更新用户的权限
     *
     * @param authority 权限对象
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("update")
    public R updateAuth(@RequestBody Authority authority) {
        authorityService.update(authority);
        return new R(HttpStatus.HTTP_OK, "修改权限成功");
    }

}
