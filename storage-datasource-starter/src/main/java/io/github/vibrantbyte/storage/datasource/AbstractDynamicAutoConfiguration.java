package io.github.vibrantbyte.storage.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import io.github.vibrantbyte.storage.datasource.sharding.ShardingConfiguration;
import io.github.vibrantbyte.storage.datasource.sharding.ShardingJdbcConfig;
import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author vibrant byte
 */
public abstract class AbstractDynamicAutoConfiguration implements IDynamicAutoConfiguration {

    /**
     * 为了方便单元测试添加
     */
    protected Map<String, List<MasterSlaveDataSource>> allDataSources = new HashMap<String, List<MasterSlaveDataSource>>();

    private DataSource createDruidDataSource(DynamicDataSourceConfig.DataSourceConfig dataSourceItem) {
        try {
            DruidDataSource datasource = new DruidDataSource();
            datasource.setUrl(dataSourceItem.getUrl());
            datasource.setName(dataSourceItem.getName());
            datasource.setUsername(dataSourceItem.getUsername());
            datasource.setPassword(dataSourceItem.getPassword());
            // 属性设置
            datasource.setInitialSize(dataSourceItem.getInitialSize());
            datasource.setMaxActive(dataSourceItem.getMaxActive());
            datasource.setMinIdle(dataSourceItem.getMinIdle());
            datasource.setMaxWait(dataSourceItem.getMaxWait());
            datasource.setPoolPreparedStatements(dataSourceItem.isPoolPreparedStatements());
            datasource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceItem.getMaxPoolPreparedStatementPerConnectionSize());
            datasource.setValidationQuery(dataSourceItem.getValidationQuery());
            datasource.setTestOnBorrow(dataSourceItem.isTestOnBorrow());
            datasource.setTestOnReturn(dataSourceItem.isTestOnReturn());
            datasource.setTestWhileIdle(dataSourceItem.isTestWhileIdle());
            datasource.setTimeBetweenEvictionRunsMillis(dataSourceItem.getTimeBetweenEvictionRunsMillis());
            datasource.setMinEvictableIdleTimeMillis(dataSourceItem.getMinEvictableIdleTimeMillis());
            datasource.setFilters(dataSourceItem.getFilters());

            Properties properties = new Properties();
            properties.setProperty("druid.stat.mergeSql", "true");
            properties.setProperty("druid.stat.slowSqlMills", "1000");
            datasource.setConnectProperties(properties);
            datasource.setUseGlobalDataSourceStat(true);
            return datasource;
        } catch (Exception e) {
            throw new DataSourceCreateException(e);
        }
    }

    /**
     * 获取数据源配置
     * @return
     */
    protected abstract DynamicDataSourceConfig getDataSourceConfig();

    /**
     * 获取sharding配置
     * @return
     */
    protected abstract ShardingJdbcConfig getShardingJdbcConfig();

    private MasterSlaveRuleConfiguration createMasterSlaveConfig(String master, List<String> slaves, String name) {
        MasterSlaveRuleConfiguration configuration = new MasterSlaveRuleConfiguration();
        configuration.setMasterDataSourceName(master);
        configuration.setSlaveDataSourceNames(slaves);
        configuration.setName(name);

        return configuration;
    }

    private DataSource createGroupDatasource(ShardingConfiguration sharding, List<MasterSlaveDataSource> dataSources) {
        try {
            if (null == sharding) {
                //没有配置散库散表
                /**
                 * 至少得配置一个主库，如果连一个库都没有，就抛错
                 */
                if (dataSources.size() != 1) {
                    throw new DataSourceCreateException("Invalid Configuration, Duplicated Master Slaves in mysql-environment.yml!");
                }

                MasterSlaveDataSource dataSource = dataSources.get(0);
                /**
                 * 仅一主
                 */
                if (CollectionUtils.isEmpty(dataSource.getSlaves())) {
                    return dataSource.getMaster();
                } else {
                    /**
                     * 创建一主多从型结构
                     */
                    Map<String, DataSource> dataSourceMap = new HashMap<>();
                    dataSourceMap.put(dataSource.getMasterName(), dataSource.getMaster());
                    dataSourceMap.putAll(dataSource.getSlaves());

                    return MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, createMasterSlaveConfig(dataSource.getMasterName(), dataSource.getSlaves().entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList()), dataSource.getName()), new ConcurrentHashMap<String, Object>());
                }
            } else {
                /**
                 * 过滤主从库
                 */
                List<MasterSlaveRuleConfiguration> masterSlaveRuleConfigurations = new ArrayList<>();
                dataSources.stream().filter(dataSource -> null != dataSource.getSlaves() && dataSource.getSlaves().size() > 0)
                        .forEach(
                                dataSource -> masterSlaveRuleConfigurations.add(createMasterSlaveConfig(dataSource.getMasterName(), dataSource.getSlaves().entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList()), dataSource.getName()))
                        );

                Map<String, DataSource> dataSourceMap = new HashMap<>();
                dataSources.forEach(dataSource -> {
                    dataSourceMap.put(dataSource.getMasterName(), dataSource.getMaster());
                    dataSourceMap.putAll(dataSource.getSlaves());
                });

                ShardingRuleConfiguration shardingRuleConfiguration = sharding.getShardingRuleConfiguration();

                if (masterSlaveRuleConfigurations.size() > 0) {
                    shardingRuleConfiguration.setMasterSlaveRuleConfigs(masterSlaveRuleConfigurations);
                }

                return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfiguration, new ConcurrentHashMap<>(), sharding.getProps());
            }
        } catch (SQLException e) {
            throw new DataSourceCreateException(e, extractDataSourcesFromMasterSlaveDataSources(dataSources));
        }
    }

    private List<DataSource> extractDataSourcesFromMasterSlaveDataSources(List<MasterSlaveDataSource> masterSlaveDataSources) {
        List<DataSource> dataSources = new ArrayList<>();
        if (masterSlaveDataSources == null) {
            return dataSources;
        }
        masterSlaveDataSources.forEach(masterSlaveDataSource -> {
            dataSources.add(masterSlaveDataSource.getMaster());
            if (masterSlaveDataSource.getSlaves() != null) {
                masterSlaveDataSource.getSlaves().forEach((name, slaveDataSource) -> dataSources.add(slaveDataSource));
            }
        });
        return dataSources;
    }

    private MasterSlaveDataSource create(String name, DynamicDataSourceConfig.DataSourceConfig master, List<DynamicDataSourceConfig.DataSourceConfig> slaves) {
        MasterSlaveDataSource dataSource = new MasterSlaveDataSource();
        if (null != master) {
            dataSource.setMasterName(master.getName());
            dataSource.setMaster(createDruidDataSource(master));
        }

        if (!CollectionUtils.isEmpty(slaves)) {
            Map<String, DataSource> map = new HashMap<>();
            slaves.forEach(slave -> map.put(slave.getName(), createDruidDataSource(slave)));
            dataSource.setSlaves(map);
        }

        dataSource.setName(name);

        return dataSource;
    }

    /**
     * 创建每一个数据源组里的主从数据源集合
     *
     * @param group
     * @return
     */
    private List<MasterSlaveDataSource> create(DynamicDataSourceConfig.DataSourceConfigGroup group) {
        List<MasterSlaveDataSource> list = new ArrayList<>();

        /**
         * 如果是配置了多组主从，则创建多组主从数据源集合；如果不是，则创建单组的主从数据源
         */
        if (!CollectionUtils.isEmpty(group.getMasterSlaves())) {
            group.getMasterSlaves().forEach(config -> list.add(create(config.getName(), config.getMaster(), config.getSlaves())));
        } else {
            list.add(create(group.getName(), group.getMaster(), group.getSlaves()));
        }

        allDataSources.put(group.getName(), list);

        return list;
    }

    protected Map<Object, Object> createTargetDataSources() {
        DynamicDataSourceConfig dataSourceConfig = getDataSourceConfig();
        if (null == dataSourceConfig)
            throw new DataSourceCreateException("No valid datasource config found!");

        ShardingJdbcConfig shardingConfig = getShardingJdbcConfig();

        Map<Object, Object> dataSourceMap = new HashMap<>();

        dataSourceConfig.getGroups().stream().forEach(
                group -> {
                    List<MasterSlaveDataSource> dataSources = create(group);
                    ShardingConfiguration sharding = shardingConfig.getShardingConfig(group.getName());
                    DataSource dataSource = createGroupDatasource(sharding, dataSources);
                    dataSourceMap.put(group.getName(), dataSource);
                }
        );
        return dataSourceMap;
    }

    public GroupedDynamicDataSource create() {
        Map<Object, Object> dataSourceMap = createTargetDataSources();

        GroupedDynamicDataSource dataSource = new GroupedDynamicDataSource();
        dataSource.setTargetDataSources(dataSourceMap);
        return dataSource;
    }
}
