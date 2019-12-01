package io.github.vibrantbyte.storage.datasource.sharding;

import io.shardingjdbc.core.yaml.sharding.YamlShardingRuleConfiguration;

/**
 * @author vibrant byte
 */
public class ShardingConfiguration extends YamlShardingRuleConfiguration {

    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
