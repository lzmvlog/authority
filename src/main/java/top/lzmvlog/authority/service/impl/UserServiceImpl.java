package top.lzmvlog.authority.service.impl;

import cn.hutool.core.lang.UUID;
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
     *
     * @return
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
        log.info("user:{}",user.toString());
        return userMapper.insert(new User()
                .setId(String.valueOf(UUID.fastUUID()))
                .setName(user.getName())
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setEnable(true));
    }

    public String selectUser(User user) throws TokenException {
        User user1 = userMapper.selectOne(Wrappers.query(user)
                .eq("name", user.getName())
                .eq("password", passwordEncoder.matches(user.getPassword(), "HS256"))
                .eq("enable", true));
        if (user1 == null)
            throw new TokenException(HttpStatus.HTTP_BAD_REQUEST,"用户信息错误");
        return jwtUtil.createToken(user.getName());
    }
}
