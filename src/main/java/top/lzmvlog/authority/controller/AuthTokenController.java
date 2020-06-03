package top.lzmvlog.authority.controller;

import cn.hutool.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.lzmvlog.authority.model.User;
import top.lzmvlog.authority.service.impl.UserServiceImpl;
import top.lzmvlog.authority.util.data.Response;

/**
 * @author ShaoJie
 * @Date 2020年05月12 10:41
 * @Description: 资源权限
 */
@RestController
public class AuthTokenController {

    /**
     * 用户业务逻辑层
     */
    @Autowired
    public UserServiceImpl userService;

    @PostMapping("oauth/token")
    public Response getToken(@RequestBody User user) {
        return new Response(HttpStatus.HTTP_OK, "登录成功", userService.selectUser(user));
    }

}