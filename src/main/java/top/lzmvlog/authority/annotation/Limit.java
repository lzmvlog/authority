package top.lzmvlog.authority.annotation;

import java.lang.annotation.*;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2020年12月18日 15:10
 * @Description: 限流注解 --> 对接限流
 * 原理：1．令牌以一定的速率放入桶中。
 * 2．每个令牌允许源发送一定数量的比特。
 * 3．发送一个包，流量调节器就要从桶中删除与包大小相等的令牌数。
 * 4．如果没有足够的令牌发送包，这个包就会等待直到有足够的令牌（在整形器的情况下）或者包被丢弃，也有可能被标记更低的DSCP（在策略者的情况下）。
 * 5．桶有特定的容量，如果桶已经满了，新加入的令牌就会被丢弃。因此，在任何时候，源发送到网络上的最大突发数据量与桶的大小成比例。令牌桶允许突发，但是不能超过限制。
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    /**
     * 默认每秒放入桶中的token
     */
    double limitNum() default 20;

    /**
     * 限流名称
     */
    String name() default "";
}
