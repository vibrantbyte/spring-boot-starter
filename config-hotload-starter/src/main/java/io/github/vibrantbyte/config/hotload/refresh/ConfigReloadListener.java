package io.github.vibrantbyte.config.hotload.refresh;

import org.springframework.context.ApplicationListener;

import java.util.Set;

/**
 *
 * @author vibrant byte
 *
 */
public abstract class ConfigReloadListener implements ApplicationListener<ConfigReloadedEvent> {

    @Override
    public void onApplicationEvent(ConfigReloadedEvent event) {
        if (!shouldRefresh(event.getFiles())) {
            return;
        }
        doRefresh(event.getFiles());
    }

    protected abstract boolean shouldRefresh(Set<String> files);

    protected abstract void doRefresh(Set<String> files);
}
