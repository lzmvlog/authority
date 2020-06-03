package top.lzmvlog.authority.util.jwt;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.lzmvlog.authority.util.date.DateUtil;

/**
 * @author ShaoJie
 * @Date 2020年05月12 15:16
 * @Description: 生成解析 token 的工具类
 */
@Component
public class JwtUtil {

    /**
     * 签名密钥
     */
    @Value("${auth.token.signingKey}")
    private String signingKey;

    /**
     * 创建生成 token
     *
     * @return
     */
    public String createToken(String account) {
        return Jwts.builder()
                // 设置唯一的 ida
                .setId(IdUtil.simpleUUID())
                // 设置主题
                .setSubject(account)
                // 设置过期时间
                .setExpiration(new DateUtil().getNowDateOneTime())
                // 设置 token 签发的时间
                .setIssuedAt(new DateTime())
                // 设置签名 使用HS256算法，并设置SecretKey(字符串)  签名算法和秘钥
                .signWith(SignatureAlgorithm.HS256, signingKey)
                // 以下内容构建JWT并将其序列化为紧凑的，URL安全的字符串
                .compact();
    }

}
