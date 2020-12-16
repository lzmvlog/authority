package top.lzmvlog.authority.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.lzmvlog.authority.annotation.IdAnnoImpl;

import java.util.List;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2020年12月14日 22:38
 * @Description:
 */
//@Configuration
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    IdAnnoImpl idAnno;

    /**
     * 添加自定义的拦截器
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(idAnno);
    }

}
