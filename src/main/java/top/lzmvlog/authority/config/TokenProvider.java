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

    private static final String AUTHORITIES_KEY = "auth";

    @Value("${auth.token.signingKey}")
    private String signingKey;

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();
        Object t = claims.get(AUTHORITIES_KEY);
        log.info("t:{}",t);
        String auth = "";
        if (Objects.nonNull(t)) {
            auth = t.toString();
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(auth.split(","))
                        .filter(StringUtils::isNotBlank)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        User principal = new User(claims.getSubject(), "12345", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

}
