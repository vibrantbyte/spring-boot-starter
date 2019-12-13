package io.github.vibrantbyte.storage.datasource.aop;

import java.lang.annotation.*;

/**
 *
 * 如果使用 #{} 表示需要从参数中获取，如果不是直接使用这个值
 * JDK代理，不能获取接口中的参数。
 *
 * @author xiaoyueya
 */
@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceSelector {

    /**
     * 数据源切换
     * @return
     */
    String name() default "default";

    /**
     * 强制使用主库
     * @return
     */
    boolean mandatoryMaster() default false;
}
