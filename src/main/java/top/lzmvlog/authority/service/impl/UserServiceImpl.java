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

import javax.validation.constraints.NotNull;

/**
 * @author ShaoJie
 * @Date 2020年05月12 10:20
 * @Description: User 业务逻辑
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
     * @return 用户的 token
     * @throws TokenException 登录失败 / 分发 token 失败
     */
    public String selectUser(User user) throws TokenException {
        User userInfo = userMapper.selectOne(Wrappers.query(new User().setName(user.getName())));
        // matches(CharSequence rawPassword, String encodedPassword) 第一个参数是当前输入的密码 第二个是数据库中已经加密过的密文
        if (userInfo == null || !passwordEncoder.matches(user.getPassword(), userInfo.getPassword()))
            throw new TokenException(HttpStatus.HTTP_BAD_REQUEST, "用户信息错误");
        return jwtUtil.createToken(user.getName());
    }

    /**
     * 查询用户信息
     *
     * @param name 用户名称
     * @return 用户的 token
     * @throws TokenException 登录失败 / 分发 token 失败
     */
    @Override
    public User loadUserByUsername(@NotNull String name) throws TokenException {
        User userInfo = userMapper.selectOne(Wrappers.query(new User().setName(name)));
        // matches(CharSequence rawPassword, String encodedPassword) 第一个参数是当前输入的密码 第二个是数据库中已经加密过的密文
        if (userInfo == null)
            throw new TokenException(HttpStatus.HTTP_BAD_REQUEST,"没有当前账号信息");

        return userInfo;
    }

    /**
     * 查询用户信息
     *
     * @param account 用户账号
     * @return 用户的基本信息
     */
    @Override
    public User selectUserInfo(String account) {
        return userMapper.selectOne(Wrappers.query(new User().setName(account)));
    }

    /**
     * 查询是否存在当前的用户
     *
     * @param account 用户账号
     * @return 当前的用户是否存在
     */
    @Override
    public boolean getUser(String account) {
        Integer count = userMapper.selectCount(Wrappers.query(new User().setName(account)));
        if (count == 0)
            return false;
        return true;
    }

}
