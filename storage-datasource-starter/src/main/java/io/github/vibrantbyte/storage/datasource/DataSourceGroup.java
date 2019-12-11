package io.github.vibrantbyte.storage.datasource;

import java.lang.annotation.*;

/**
 * @author xiaoyueya
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceGroup {

    String name() default "default";
}
