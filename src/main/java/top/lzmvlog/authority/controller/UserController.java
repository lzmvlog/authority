package top.lzmvlog.authority.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.lzmvlog.authority.annotation.Id;
import top.lzmvlog.authority.exception.ServiceException;
import top.lzmvlog.authority.model.Resource;
import top.lzmvlog.authority.model.User;
import top.lzmvlog.authority.service.UserService;
import top.lzmvlog.authority.upload.QiUpload;
import top.lzmvlog.authority.util.data.R;

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
//        return new R(HttpStatus.HTTP_OK, "注册成功");
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
        return new R(HttpStatus.HTTP_OK, "保存用户成功");
    }

    /**
     * 查询用户信息
     *
     * @param user 用户的信息
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("getInfo")
    public R getUserInfo(@NotNull(message = "用户信息不能为空") User user, Page page) {
        return new R(HttpStatus.HTTP_OK,
                userService.selectUserByUser(page, user));
    }

    /**
     * 查询用户信息
     *
     * @param page 分页信息
     * @return list 用户信息
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("getUser")
    public R getUserList(Page page) {
        return new R(HttpStatus.HTTP_OK,
                userService.selectUserList(page));
    }

    /**
     * 禁用用户
     *
     * @param user 用户信息
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("disable")
    public R disableUser(User user) {
        userService.disableUser(user);
        return new R(HttpStatus.HTTP_OK, "禁用成功");
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
        return new R(HttpStatus.HTTP_OK, "上传头像成功");
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
        return new R(HttpStatus.HTTP_OK, "更新信息成功");
    }

    /**
     * 查询自己的信息
     *
     * @param id 用户信息
     * @return user 用户对象
     */
    @GetMapping("selectOneself")
    public R selectOneself(@Id String id) {
        return new R(HttpStatus.HTTP_OK, userService.selectOne(new User().setId(id)));
    }

}
