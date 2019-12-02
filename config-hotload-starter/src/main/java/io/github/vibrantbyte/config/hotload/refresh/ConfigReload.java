package io.github.vibrantbyte.config.hotload.refresh;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 *
 * @author vibrant byte
 * @date 2017/6/13
 */
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@RefreshScope
@Configuration
public @interface ConfigReload {
}
