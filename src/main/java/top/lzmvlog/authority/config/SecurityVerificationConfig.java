package top.lzmvlog.authority.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ShaoJie
 * @Date 2020年05月12 14:36
 * @Description: 安全验证配置
 */
@Configuration
public class SecurityVerificationConfig extends WebSecurityConfigurerAdapter {

    /**
     * 构建用户验证的配置
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }

    /**
     * 授权 、 验证
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

}
