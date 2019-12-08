package io.github.vibrantbyte.config.hotload.refresh;

import org.springframework.context.ApplicationEvent;

import java.util.Set;

/**
 *
 * @author vibrant byte
 *
 */
public class ConfigReloadedEvent extends ApplicationEvent {

    private Set<String> files;

    public ConfigReloadedEvent(Object source, Set<String> files) {
        super(source);
        this.files = files;
    }

    public Set<String> getFiles() {
        return this.files;
    }
}
