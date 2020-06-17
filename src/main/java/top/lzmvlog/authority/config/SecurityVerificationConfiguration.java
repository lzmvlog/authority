package top.lzmvlog.authority.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.lzmvlog.authority.filter.JwtAuthenticationFilter;
import top.lzmvlog.authority.handler.JwtAccessDeniedHandler;

/**
 * @author ShaoJie
 * @Date 2020年05月12 14:36
 * @Description: 安全验证配置
 */
@Configuration
@EnableWebSecurity
public class SecurityVerificationConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 密码加密
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 拦截器
     */
    @Autowired
    public JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * jwt 验证处理器
     */
    @Autowired
    public JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    public TokenConfiguration tokenConfiguration;

    /**
     * 构建用户验证的配置
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 授权 、 验证
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/auth/token").permitAll()
                .antMatchers("/user/registered").permitAll()
                .anyRequest().authenticated()
                .and()
//                .addFilter(jwtAuthenticationFilter)
                .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .apply(tokenConfiguration)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }

}
