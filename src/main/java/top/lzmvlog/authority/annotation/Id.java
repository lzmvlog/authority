package top.lzmvlog.authority.annotation;

import java.lang.annotation.*;

/**
 * @author ShaoJie
 * @Date 2020年07月30 17:26
 * @Description: 获取上下文中的用户信息
 * <p>
 * 用于获取 Spring Context 上下文中的用户 id -- 未完成
 */
//@Target(ElementType.FIELD)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
//@Inherited()
@Documented
public @interface Id {

    String value() default "id";
}
