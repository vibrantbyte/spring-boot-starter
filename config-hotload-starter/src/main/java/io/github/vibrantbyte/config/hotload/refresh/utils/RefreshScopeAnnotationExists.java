package io.github.vibrantbyte.config.hotload.refresh.utils;

import io.github.vibrantbyte.config.hotload.refresh.ConfigReload;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 *
 * @author vibrant byte
 *
 */
public class RefreshScopeAnnotationExists implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String[] beans = context.getBeanFactory().getBeanNamesForAnnotation(ConfigReload.class);
        return null != beans && beans.length > 0;
    }
}
