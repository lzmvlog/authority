package top.lzmvlog.authority.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lzmvlog.authority.dao.UserMapper;
import top.lzmvlog.authority.exception.TokenException;
import top.lzmvlog.authority.model.User;
import top.lzmvlog.authority.model.vo.TokenVo;
import top.lzmvlog.authority.service.PurviewService;
import top.lzmvlog.authority.service.UserService;
import top.lzmvlog.authority.util.date.DateUtil;
import top.lzmvlog.authority.util.jwt.JwtUtil;

import java.time.LocalDateTime;
import java.util.Map;

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
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    /**
     * 签发 token 的工具类
     */
    @Autowired
    public JwtUtil jwtUtil;

    /**
     * 权限业务逻辑
     */
    @Autowired
    PurviewService purviewService;

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
        user.setId(IdUtil.fastSimpleUUID())
                // encode() 对明文密码加密
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setEnable(true);
        return userMapper.insert(user);
    }

    /**
     * 查询用户信息
     *
     * @param user 用户信息
     * @return 用户的 token
     * @throws TokenException 登录失败 / 分发 token 失败
     */
    @Override
    public TokenVo selectUser(User user) {
        User userInfo = userMapper.selectOne(Wrappers.query(new User().setAccount(user.getAccount())));

        // matches(CharSequence rawPassword, String encodedPassword) 第一个参数是当前输入的密码 第二个是数据库中已经加密过的密文
        if (userInfo == null || !passwordEncoder.matches(user.getPassword(), userInfo.getPassword()))
            throw new TokenException(HttpStatus.HTTP_BAD_REQUEST, "用户信息错误，填写正确的账号密码");

        if (!userInfo.getEnable())
            throw new TokenException(HttpStatus.HTTP_FORBIDDEN, "该用户被禁止访问");

        // 获取权限 map
        Map<String, Object> map = purviewService.selectList(userInfo.getId());

        // 生成 token
        String token = jwtUtil.createToken(map);

        return new TokenVo(token, userInfo.getName(), DateUtil.getNowDateOneTime(), LocalDateTime.now());
    }

    /**
     * 查询用户信息
     *
     * @param userPage 分页信息
     * @param user     用户名称
     * @return 用户的 token
     * @throws TokenException 登录失败 / 分发 token 失败
     */
    @Override
    public IPage<User> selectUserByUser(Page userPage, User user) {
        IPage<User> users = userMapper.selectPage(userPage, Wrappers.query(user));
        // matches(CharSequence rawPassword, String encodedPassword) 第一个参数是当前输入的密码 第二个是数据库中已经加密过的密文
        if (users == null)
            throw new TokenException(HttpStatus.HTTP_BAD_REQUEST, "没有当前账号信息");

        return users;
    }

    /**
     * 查询用户信息
     *
     * @param userPage 分页信息
     * @return list 用户信息
     */
    @Override
    public IPage selectUserList(Page userPage) {
        return userMapper.selectPage(userPage, Wrappers.query());
    }

    /**
     * 查询是否存在当前的用户
     *
     * @param id 用户账号
     * @return boolean 当前的用户是否存在
     */
    @Override
    public User getUser(String id) {
        return userMapper.selectOne(Wrappers.query(new User().setId(id)));
    }

    /**
     * 禁用用户
     *
     * @param user 用户信息
     */
    @Override
    public void disableUser(User user) {
        user.setEnable(false);
        userMapper.update(user, Wrappers.update());
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     */
    @Override
    public void update(User user) {
        userMapper.updateById(user);
    }

    /**
     * 查询用户自己的信息
     *
     * @param id
     * @return
     */
    @Override
    public User selectOne(String id) {
        return userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, id));
    }

}
