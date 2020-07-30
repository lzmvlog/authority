package top.lzmvlog.authority.filter;

import cn.hutool.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.lzmvlog.authority.config.TokenProvider;
import top.lzmvlog.authority.exception.TokenException;
import top.lzmvlog.authority.service.UserService;
import top.lzmvlog.authority.util.jwt.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ShaoJie
 * @Date 2020年06月08 20:10
 * @Description: jwt 权限拦截器
 */
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * 用户的业务逻辑层
     */
    @Autowired
    public UserService userService;

    @Autowired
    public JwtUtil jwtUtil;

    @Autowired
    private TokenProvider tokenProvider;

    public JwtAuthenticationFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!"/auth/token".equals(httpServletRequest.getRequestURI()) && !"/user/registered".equals(httpServletRequest.getRequestURI())) {
            String token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
            if (token == null)
                throw new TokenException(HttpStatus.HTTP_FORBIDDEN, "缺少验证信息");

//            log.info("校验的 token：{}", token);
//            String account = jwtUtil.parseToken(token);
//            log.info("token 解析到的用户账号：{}", account);
//            if (account == null)
//                throw new TokenException(HttpStatus.HTTP_FORBIDDEN, "不存在当前的用户");
//            boolean isPresence = userService.getUser(account);
//            if (!isPresence)
//                throw new TokenException(HttpStatus.HTTP_INTERNAL_ERROR, "token 出错");

            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
