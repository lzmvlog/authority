package top.lzmvlog.authority.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ShaoJie
 * @Date 2020年06月17 20:10
 * @Description:
 */
@Slf4j
@Component
public class TokenProvider {

    // 权限密钥
    private static final String AUTHORITIES_KEY = "auth";

    // 签名密钥
    @Value("${auth.token.signingKey}")
    private String signingKey;

    /**
     * 获取 Spring Context 的 SecurityContext 对象
     * 用于获取用户的身份验证
     *
     * @param token jwt 生成的 token 信息
     * @return authentication 认证对象
     */
    public Authentication getAuthentication(String token) {
        // parser() 解析token
        Claims claims = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();
        Object claim = claims.get(AUTHORITIES_KEY);
        log.info("claim:{}", claim);
        String auth = "";
        if (Objects.nonNull(claim)) {
            auth = claim.toString();
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(auth.split(","))
                        .filter(StringUtils::isNotBlank)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // 创建 Spring Security 的 user 对象
        User principal = new User(claims.getSubject(), "", authorities);
        // 创建返回 Authentication 对象
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

}
