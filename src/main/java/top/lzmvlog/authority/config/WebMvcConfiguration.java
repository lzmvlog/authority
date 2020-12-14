package top.lzmvlog.authority.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import top.lzmvlog.authority.annotation.IdAnnoImpl;

import java.util.List;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2020年12月14日 22:38
 * @Description:
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    /**
     * 添加自定义的拦截器
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new IdAnnoImpl());
    }

}
