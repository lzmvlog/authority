package top.lzmvlog.authority.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lzmvlog.authority.model.User;
import top.lzmvlog.authority.service.UserService;
import top.lzmvlog.authority.util.PageUtil;
import top.lzmvlog.authority.util.data.Response;

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
     * 注册
     *
     * @param user 用户的信息
     * @return
     */
    @PostMapping("/registered")
    public Response registered(User user) {
        if (user == null)
            throw new RuntimeException("用户不能为空");

        userService.insert(user);
        return new Response(HttpStatus.HTTP_OK, "注册成功");
    }

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return
     */
    @PostMapping("save")
    public Response save(@NotNull User user) {
        return new Response(HttpStatus.HTTP_OK, userService.insert(user));
    }

    /**
     * 查询用户信息
     *
     * @param user 用户的信息
     * @return
     */
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/getInfo")
    public Response getUserInfo(@NotNull(message = "用户信息不能为空") User user, PageUtil pageUtil) {
        return new Response(HttpStatus.HTTP_OK,
                userService.selectUserByUser(new Page<>(pageUtil.getPage(), pageUtil.getPageNum()), user));
    }

}
