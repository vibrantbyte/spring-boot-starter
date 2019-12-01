package io.github.vibrantbyte.storage.datasource.sharding;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author vibrant byte
 */
@Configuration
@ConfigurationProperties(
        prefix = "sharding-jdbc"
)
@PropertySource(
        value = {"file:${spring.config.location}/mysql/sharding-jdbc-${spring.profiles.active}.yml", "classpath:mysql/sharding-jdbc-${spring.profiles.active}.yml"},
        ignoreResourceNotFound = true,
        factory = YamlPropertyLoaderFactory.class
)
public class ShardingJdbcConfig {

    public ShardingJdbcConfig() {
    }

    private List<ShardingConfiguration> shardings;

    private Map<String, ShardingConfiguration> shardingsMap;

    @PostConstruct
    public void init() {
        if (shardings != null) {
            shardingsMap = shardings.stream().collect(Collectors.toMap(ShardingConfiguration::getGroupName, Function.identity()));
        } else {
            shardingsMap = new HashMap<>();
        }
    }

    public List<ShardingConfiguration> getShardings() {
        return shardings;
    }

    public void setShardings(List<ShardingConfiguration> shardings) {
        this.shardings = shardings;
    }

    public Map<String, ShardingConfiguration> getShardingsMap() {
        return shardingsMap;
    }

    public ShardingConfiguration getShardingConfig(String group) {
        return shardingsMap.get(group);
    }
}
