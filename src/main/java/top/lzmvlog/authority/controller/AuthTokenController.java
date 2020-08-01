package top.lzmvlog.authority.controller;

import cn.hutool.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lzmvlog.authority.model.User;
import top.lzmvlog.authority.service.UserService;
import top.lzmvlog.authority.util.data.Response;

/**
 * @author ShaoJie
 * @Date 2020年05月12 10:41
 * @Description: 资源权限 管理
 */
@RestController
@RequestMapping("auth")
public class AuthTokenController {

    /**
     * 用户业务逻辑层
     */
    @Autowired
    public UserService userService;

    /**
     * 签发 token
     *
     * @param user 用户信息
     * @return
     */
    @GetMapping("/token")
    public Response getToken(User user) {
        return new Response(HttpStatus.HTTP_OK, "登录成功", userService.selectUser(user));
    }

}