package top.lzmvlog.authority.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import top.lzmvlog.authority.util.SecurityUtil;

/**
 * @author ShaoJie
 * @Date 2020年08月10 10:10
 * @Description:
 */
@Component
@Slf4j
public class IdImpl implements HandlerMethodArgumentResolver {

    /**
     * 此解析器是否支持给定的{@linkplain MethodParameter 方法参数}。
     *
     * @param parameter 要检查的方法参数
     * @return {@code true} 如果此解析器支持所提供的参数；
     * {@code false} 否则不支持
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Id.class);
    }

    /**
     * 将方法参数解析为给定请求的参数值。 {@link ModelAndViewContainer}提供对请求模型的访问。
     * {@link WebDataBinderFactory}提供了一种在需要进行数据绑定和类型转换时创建
     * {@link org.springframework.web.bind.WebDataBinder}实例的方法
     *
     * @param parameter     要解析的方法参数。此参数必须先前已传递给必须返回{@code true}的
     *                      {@link #supportsParameter}
     * @param mavContainer  当前请求的ModelAndViewContainer
     * @param webRequest    当前请求
     * @param binderFactory a factory for creating {@link org.springframework.web.bind.WebDataBinder} instances
     * @return the resolved argument value, or {@code null} if not resolvable
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        // 获取用户的唯一 id
        return SecurityUtil.getUser().getUsername();
    }
}