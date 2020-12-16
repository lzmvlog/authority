package top.lzmvlog.authority.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
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
@Aspect
@Component
@Slf4j
public class IdAnnoImpl implements HandlerMethodArgumentResolver {
    /**
     * Whether the given {@linkplain MethodParameter method parameter} is
     * supported by this resolver.
     *
     * @param parameter the method parameter to check
     * @return {@code true} if this resolver supports the supplied parameter;
     * {@code false} otherwise
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
     *                      {@link HandlerMethodArgumentResolver.supportParameter}
     * @param mavContainer  the ModelAndViewContainer for the current request 当前请求的ModelAndViewContainer
     * @param webRequest    the current request
     * @param binderFactory a factory for creating {@link org.springframework.web.bind.WebDataBinder} instances
     * @return the resolved argument value, or {@code null} if not resolvable
     * @throws Exception in case of errors with the preparation of argument values
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return SecurityUtil.getUser().getUsername();
    }
}