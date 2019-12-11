package io.github.vibrantbyte.storage.datasource.aop;

import java.lang.annotation.*;

/**
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
