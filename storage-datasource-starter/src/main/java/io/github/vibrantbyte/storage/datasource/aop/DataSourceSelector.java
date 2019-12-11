package io.github.vibrantbyte.storage.datasource.aop;

import io.github.vibrantbyte.config.hotload.refresh.autoconfigure.condition.OnConditionalOnFile;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author xiaoyueya
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnConditionalOnFile.class)
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
