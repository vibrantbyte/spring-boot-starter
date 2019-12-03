package io.github.vibrantbyte.config.hotload.refresh.config;

import io.github.vibrantbyte.config.hotload.refresh.ConfigReload;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

@Data
@ConfigurationProperties(prefix = "business")
@ConfigReload
public class Business {

    private String first;

    private String second;

    @PostConstruct
    public void setup() {
        System.out.println("business->" + first + ":" + second);
    }
}
