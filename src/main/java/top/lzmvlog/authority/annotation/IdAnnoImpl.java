package top.lzmvlog.authority.annotation;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author ShaoJie
 * @Date 2020年08月10 10:10
 * @Description:
 */
@Aspect
@Component
public class IdAnnoImpl {

    /**
     * 切入点
     */
    @Pointcut("@annotation(top.lzmvlog.authority.annotation.Id)")
    private void obtain() {

    }

    /**
     * 前置通知
     */
    @Before("obtain()")
    public void before() {

    }

    /**
     * 后置通知
     */
    @After("obtain()")
    public void after() {

    }

}
