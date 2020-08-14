package top.lzmvlog.authority.util;

import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import top.lzmvlog.authority.model.User;
import top.lzmvlog.authority.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Shao Jie
 * @Date 2020年08月13 16:31
 * @Description: 获取用户信息
 * <p>
 * 通过当前的工具类获取 放入 security 中的信息
 */
@UtilityClass
public class SecurityUtil {

    @Autowired
    UserService userService;

    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     *
     * @param authentication 认证对象
     * @return user 用户信息
     */
    public User getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        }
        return null;
    }

    /**
     * 获取用户
     */
    public User getUser() {
        Authentication authentication = getAuthentication();
        return getUser(authentication);
    }

    /**
     * 获取用户角色信息
     *
     * @return 角色集合
     */
    public List<String> getRoles() {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roleIds = new ArrayList<>();
        authorities.stream()
                .filter(granted -> StrUtil.startWith(granted.getAuthority(), "ROLE_"))
                .forEach(granted -> {
                    String role = StrUtil.removePrefix(granted.getAuthority(), "ROLE_");
                    roleIds.add(role);
                });
        return roleIds;
    }

}