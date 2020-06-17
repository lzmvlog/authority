package top.lzmvlog.authority.controller;

import cn.hutool.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lzmvlog.authority.model.User;
import top.lzmvlog.authority.service.impl.UserServiceImpl;
import top.lzmvlog.authority.util.data.Response;

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
    public UserServiceImpl userService;

    /**
     * 注册
     *
     * @param user 用户的信息
     * @return
     */
    @PostMapping("/registered")
    public Response registered(@RequestBody User user) {
        if (user == null)
            throw new RuntimeException("用户不能为空");
        userService.insert(user);
        return new Response(HttpStatus.HTTP_OK, "注册成功");
    }

    /**
     * 查询用户信息
     *
     * @param account 用户的账号
     * @return
     */
    @PostMapping("/getInfo")
    public Response getUserInfo(String account) {
        return new Response(HttpStatus.HTTP_OK,userService.selectUserInfo(account));
    }

}
