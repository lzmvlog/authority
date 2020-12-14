package top.lzmvlog.authority.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.lzmvlog.authority.filter.JwtAuthenticationFilter;
import top.lzmvlog.authority.handler.JwtAccessDeniedHandler;
import top.lzmvlog.authority.model.Purview;
import top.lzmvlog.authority.service.PurviewService;

import java.util.List;

/**
 * @author ShaoJie
 * @Date 2020年05月12 14:36
 * @Description: 安全验证配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
     * 异常处理器
     */
    @Autowired
    public JwtAccessDeniedHandler jwtAccessDeniedHandler;

    /**
     * toekn 配置
     */
    @Autowired
    public TokenConfiguration tokenConfiguration;

    @Autowired
    public WebMvcConfiguration webMvcConfiguration;

    @Autowired
    PurviewService purviewService;

    /**
     * 构建用户验证的配置
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 授权 、 验证
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 添加权限
        selectPurview(http);

        http
                .authorizeRequests()
                // 授权地址不需要验证
                .antMatchers("/auth/token").permitAll()
                // 其余的都需要校验
                .anyRequest().authenticated()
                .and()
//                .addFilter(jwtAuthenticationFilter)
                // 添加后置处理拦截器
                .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // 允许配置异常处理
                .exceptionHandling()
                // 访问拒绝处理程序
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .apply(tokenConfiguration)
                .and()
                // 取消 session 的状态
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }

    /**
     * 查询权限并将权限放入 security 中
     *
     * @param http
     * @throws Exception
     */
    public void selectPurview(HttpSecurity http) throws Exception {
        List<Purview> purviews = purviewService.selectList();
        for (Purview purview : purviews) {
            http.authorizeRequests()
                    .antMatchers(purview.getAuthority()).hasAnyAuthority(purview.getRole());
        }
    }

}
