package io.github.vibrantbyte.config.hotload.refresh.config;

import io.github.vibrantbyte.config.hotload.refresh.ConfigReload;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

@Data
@ConfigurationProperties(prefix = "config")
@ConfigReload
public class Config {

    private String id;

    private String name;

    @PostConstruct
    public void setup() {
        System.out.println("config -> id:" + id + ",name:" + name);
    }
}
