package io.github.vibrantbyte.config.hotload.refresh.autoconfigure.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 替换Spring ConditionalOnResource，
 * 支持多文件目录扫描，如果文件不存在，跳过继续扫描
 * @author vibrant byte
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnConditionalOnFile.class)
public @interface ConditionalOnFile {

    /**
     * The resources that must be present.
     * @return the resource paths that must be present.
     */
    String[] resources() default {};
}
