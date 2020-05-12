package top.lzmvlog.authority.service.impl;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lzmvlog.authority.dao.UserMapper;
import top.lzmvlog.authority.service.UserService;

/**
 * @author ShaoJie
 * @Date 2020年05月12 10:20
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 用户 接口
     */
    @Autowired
    public UserMapper userMapper;

}
