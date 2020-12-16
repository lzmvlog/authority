package top.lzmvlog.authority.controller;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lzmvlog.authority.model.User;
import top.lzmvlog.authority.model.vo.TokenVo;
import top.lzmvlog.authority.service.UserService;
import top.lzmvlog.authority.util.R;
import top.lzmvlog.authority.util.key.RedisKey;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author ShaoJie
 * @Date 2020年05月12 10:41
 * @Description: 资源权限 管理
 */
@RestController
@RequestMapping("auth")
public class AuthTokenController {

    @Value("${spring.redis.token-timeout}")
    private String tokenTimeOut;

    /**
     * 用户业务逻辑层
     */
    @Autowired
    public UserService userService;

    /**
     * redisTemplate
     */
    @Autowired
    public StringRedisTemplate redisTemplate;

    /**
     * 签发 token
     *
     * @param use 用户信息
     * @return
     */
    @PostMapping("/token")
    public R getToken(User use) {
        // 将获取的 token 存放在 redis 中
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String key = MessageFormat.format(RedisKey.ACCESSTOKEN, use.getAccount());
        String accessToken = valueOperations.get(key);
        if (Objects.nonNull(accessToken)) {
            return new R(HttpStatus.HTTP_OK, JSON.parseObject(accessToken, TokenVo.class));
        }
        TokenVo tokenVo = userService.selectUser(use);

        valueOperations.set(key, tokenVo.toString(), Long.valueOf(tokenTimeOut), TimeUnit.SECONDS);
        return new R(HttpStatus.HTTP_OK, tokenVo);
    }

}