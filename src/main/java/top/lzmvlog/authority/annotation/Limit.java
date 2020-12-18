package top.lzmvlog.authority.annotation;

import java.lang.annotation.*;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2020年12月18日 15:10
 * @Description:
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    // 默认每秒放入桶中的token
    double limitNum() default 20;

    String name() default "";
}
