package top.lzmvlog.authority.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ShaoJie
 * @Date 2020年06月08 20:52
 * @Description: 权限不足时处理器
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 当用户在没有授权的情况下访问受保护的REST资源时，将调用此方法发送403 Forbidden响应
        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
    }

}
