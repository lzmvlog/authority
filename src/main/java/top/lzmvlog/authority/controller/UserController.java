package top.lzmvlog.authority.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.lzmvlog.authority.annotation.Id;
import top.lzmvlog.authority.annotation.Limit;
import top.lzmvlog.authority.exception.ServiceException;
import top.lzmvlog.authority.model.Resource;
import top.lzmvlog.authority.model.User;
import top.lzmvlog.authority.service.UserService;
import top.lzmvlog.authority.upload.QiUpload;
import top.lzmvlog.authority.util.R;

import javax.validation.constraints.NotNull;

/**
 * @author ShaoJie
 * @Date 2020年06月03 21:05
 * @Description: 用户管理
 */
@RestController
@RequestMapping("user")
public class UserController {

    /**
     * 用户的业务逻辑层
     */
    @Autowired
    public UserService userService;

    /**
     * 七牛云文件上传
     */
    @Autowired
    QiUpload qiUpload;

    /**
     * 注册
     *
     * @param user 用户的信息
     * @return
     */
//    @PostMapping("registered")
//    public R registered(User user) {
//        if (user == null)
//            throw new RuntimeException("用户不能为空");
//
//        userService.insert(user);
//        return R.ok();
//    }

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("save")
    public R save(@NotNull User user) {
        userService.insert(user);
        return R.ok();
    }

    /**
     * 查询用户信息
     *
     * @param user 用户的信息
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("selectPage")
    public R selectPage(@NotNull(message = "用户信息不能为空") User user, Page page) {
        return R.ok(userService.selectPage(page, user));
    }

    /**
     * 禁用用户
     *
     * @param userId 用户信息
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("disable")
    public R disableUser(String userId) {
        userService.disableUser(userId);
        return R.ok();
    }

    /**
     * 上传文件
     *
     * @param user          用户信息
     * @param multipartFile 文件信息
     * @return
     */
    @GetMapping("upload")
    public R upload(User user, @RequestParam("file") MultipartFile multipartFile) {
        Resource resource = qiUpload.upload(multipartFile);
        if (resource == null)
            throw new ServiceException(HttpStatus.HTTP_NOT_FOUND, "头像上传失败");

        userService.update(user);
        return R.ok();
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return
     */
    @PostMapping("update")
    public R update(User user) {
        userService.update(user);
        return R.ok();
    }

    /**
     * 查询自己的信息
     *
     * @param id 用户信息
     * @return user 用户对象
     */
    @Limit(limitNum = 2, name = "selectOneself")
    @GetMapping("selectOneself")
    public R selectOneself(@Id String id) {
        return R.ok(userService.selectOne(id));
    }

}
