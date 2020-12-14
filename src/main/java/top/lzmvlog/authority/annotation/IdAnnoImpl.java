package top.lzmvlog.authority.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import top.lzmvlog.authority.model.User;
import top.lzmvlog.authority.util.SecurityUtil;

import java.util.Objects;

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
     * Resolves a method parameter into an argument value from a given request.
     * A {@link ModelAndViewContainer} provides access to the model for the
     * request. A {@link WebDataBinderFactory} provides a way to create
     * a {@link WebDataBinder} instance when needed for data binding and
     * type conversion purposes.
     * <p>
     * 将方法参数解析为给定请求的参数值。 {@link ModelAndViewContainer}提供对请求模型的访问。
     * {@link WebDataBinderFactory}提供了一种在需要进行数据绑定和类型转换时创建
     * {@link WebDataBinder}实例的方法
     *
     * @param parameter     the method parameter to resolve. This parameter must
     *                      have previously been passed to {@link #supportsParameter} which must
     *                      have returned {@code true}.
     * @param mavContainer  the ModelAndViewContainer for the current request
     * @param webRequest    the current request
     * @param binderFactory a factory for creating {@link WebDataBinder} instances
     * @return the resolved argument value, or {@code null} if not resolvable
     * @throws Exception in case of errors with the preparation of argument values
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 获取捕获到的注解
        Id annotation = parameter.getParameterAnnotation(Id.class);
        String value = annotation.value();

        if (value == null || "".equalsIgnoreCase(value) || value.equalsIgnoreCase("userId")){
            User user = SecurityUtil.getUser();
            if (Objects.isNull(user)){
                return null;
            }
            return user.getId();
        } else if ("user".equalsIgnoreCase(value)){
            return SecurityUtil.getUser();
        }

        return value;
//
//        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
//        String id = SecurityUtil.getUser().getId();
//        request.setAttribute("id", id);
//        return id;
    }
}