package top.lzmvlog.authority.util.jwt;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.lzmvlog.authority.exception.TokenException;
import top.lzmvlog.authority.util.date.DateUtil;

import java.util.Map;

/**
 * @author ShaoJie
 * @Date 2020年05月12 15:16
 * @Description: 生成解析 token 的工具类
 */
@Component
@Slf4j
public class JwtUtil {

    /**
     * 签名密钥
     */
    @Value("${auth.token.signingKey}")
    private String signingKey;

    /**
     * 创建生成 token
     * <p>
     * setClaims() 与 setSubject() 冲突所以不设置主体信息
     *
     * @param claim 用户权限 map
     * @return String 生成的 token
     */
    public String createToken(Map<String, Object> claim) {
        return Jwts.builder()
                // 设置唯一的 ida
                .setId(IdUtil.simpleUUID())
//                .claim("auth", "admin")
                .setClaims(claim)
                // 设置过期时间
                .setExpiration(DateUtil.getNowDateOneTime())
                // 设置 token 签发的时间
                .setIssuedAt(new DateTime())
                // 设置签名 使用HS256算法，并设置SecretKey(字符串)  签名算法和秘钥
                .signWith(SignatureAlgorithm.HS256, signingKey)
                // 以下内容构建JWT并将其序列化为紧凑的，URL安全的字符串
                .compact();
    }

    /**
     * 解析当前的 token
     *
     * @param token token 信息
     * @return String token种解析到的信息
     */
    public String parseToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
        log.info("claims:{}", claims.toString());
        if (claims.size() == 0)
            throw new TokenException(HttpStatus.HTTP_INTERNAL_ERROR, "token 信息错误 重新授权");

        return claims.getSubject();
    }

}
