package top.lzmvlog.authority.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lzmvlog.authority.service.impl.UserServiceImpl;

/**
 * @author ShaoJie
 * @Date 2020年05月12 10:41
 * @Description:
 */
@RestController
@RequestMapping("user")
public class UserController {

    /**
     * 用户业务逻辑层
     */
    @Autowired
    public UserServiceImpl userService;

}
