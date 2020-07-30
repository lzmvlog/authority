package top.lzmvlog.authority.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lzmvlog.authority.service.AuthorityService;

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

}
