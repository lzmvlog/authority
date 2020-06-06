package top.lzmvlog.authority.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lzmvlog.authority.dao.UserMapper;
import top.lzmvlog.authority.exception.TokenException;
import top.lzmvlog.authority.model.User;
import top.lzmvlog.authority.service.UserService;
import top.lzmvlog.authority.util.jwt.JwtUtil;

/**
 * @author ShaoJie
 * @Date 2020年05月12 10:20
 * @Description:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    /**
     * 密码加密
     */
    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    /**
     * 用户 接口
     */
    @Autowired
    public UserMapper userMapper;

    /**
     * 签发 token 的工具类
     */
    @Autowired
    public JwtUtil jwtUtil;

    /**
     * 插入用户信息
     *
     * @param user 用户信息
     * @return
     */
    @Override
    @Transactional
    public Integer insert(User user) {
        log.info("user:{}", user.toString());
        return userMapper.insert(new User()
                .setId(IdUtil.fastSimpleUUID())
                .setName(user.getName())
                // encode() 对明文密码加密
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setEnable(true));
    }

    /**
     * 查询用户信息
     *
     * @param user 用户信息
     * @return
     * @throws TokenException 登录失败 / 分发 token 失败
     */
    public String selectUser(User user) throws TokenException {
        User userInfo = userMapper.selectOne(Wrappers.query(new User().setName(user.getName())));
        // matches(CharSequence rawPassword, String encodedPassword) 第一个参数是当前输入的密码 第二个是数据库中已经加密过的密文
        if (userInfo == null || !passwordEncoder.matches(user.getPassword(), userInfo.getPassword()))
            throw new TokenException(HttpStatus.HTTP_BAD_REQUEST, "用户信息错误");
        return jwtUtil.createToken(user.getName());
    }

}
