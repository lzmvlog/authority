package top.lzmvlog.authority.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author ShaoJie
 * @Date 2020年07月30 23:48
 * @Description:
 */
@Component
public class ApiAccessDecisionManager implements AccessDecisionManager {

    /**
     * 判断用户是否有访问资源权限
     *
     * @param authentication   用户Auth
     * @param object           FilterInvocation对象
     * @param configAttributes 资源所需权限
     * @throws AccessDeniedException 无权限Exception
     */
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException {
//        if (access) {
//            //允许通过
//            return;
//        }
//        //不允许角色访问
//        throw new AccessDeniedException("NO ALLOW");
    }

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

}