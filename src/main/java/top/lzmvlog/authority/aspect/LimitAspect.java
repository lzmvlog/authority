package top.lzmvlog.authority.aspect;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import top.lzmvlog.authority.annotation.Limit;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2020年12月18日 15:10
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class LimitAspect {

    /**
     * 限流 map
     */
    private ConcurrentHashMap<String, RateLimiter> RATE_LIMITER = new ConcurrentHashMap<>();

    /**
     * 限流
     */
    private RateLimiter rateLimiter;

    /**
     * 切入点
     */
    @Pointcut("@annotation(top.lzmvlog.authority.annotation.Limit)")
    public void serviceLimit() {
    }

    /**
     * 环绕处理
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("serviceLimit()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 获取拦截的方法名
        Signature sig = point.getSignature();
        // 获取拦截的方法名
        MethodSignature msig = (MethodSignature) sig;
        Object target = point.getTarget();
        // 为了获取注解信息
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        Limit annotation = currentMethod.getAnnotation(Limit.class);
        // 获取注解每秒加入桶中的token
        double limitNum = annotation.limitNum();
        // 注解所在方法名区分不同的限流策略
        String functionName = msig.getName();

        // 判断当前的方法名是否存在于限流中
        if (!RATE_LIMITER.containsKey(functionName)) {
            RATE_LIMITER.put(functionName, RateLimiter.create(limitNum));
        }
        rateLimiter = RATE_LIMITER.get(functionName);

        if (rateLimiter.tryAcquire()) {
            return point.proceed();
        } else {
            log.info("请求繁忙...超出系统最大承受流量");
            return null;
        }
    }
}