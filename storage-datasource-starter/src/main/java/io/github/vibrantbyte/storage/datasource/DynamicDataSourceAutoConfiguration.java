package io.github.vibrantbyte.storage.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.renrenche.config.refresh.ConfigReloadListener;
import com.renrenche.spring.boot.patch.annotation.ConditionalOnFile;
import com.renrenche.spring.boot.storage.datasource.sharding.ShardingJdbcConfig;
import io.github.vibrantbyte.storage.datasource.sharding.ShardingJdbcConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author vibrant byte
 */
@Configuration
@ConditionalOnFile(resources = {"file:${spring.config.location}/mysql/mysql-${spring.profiles.active}.yml", "classpath:mysql/mysql-${spring.profiles.active}.yml"})
@ConditionalOnClass({DataSource.class})
@Import({DynamicDataSourceConfig.class, ShardingJdbcConfig.class})
public class DynamicDataSourceAutoConfiguration extends AbstractDynamicAutoConfiguration {

    private Logger log = LoggerFactory.getLogger(DynamicDataSourceAutoConfiguration.class);

    @Autowired
    private DynamicDataSourceConfig dataSourceConfig;

    @Autowired(required = false)
    private ShardingJdbcConfig shardingJdbcConfig;

    @Autowired
    private Environment environment;

    @Override
    protected DynamicDataSourceConfig getDataSourceConfig() {
        return dataSourceConfig;
    }

    @Override
    protected ShardingJdbcConfig getShardingJdbcConfig() {
        return shardingJdbcConfig;
    }

    @Override
    @Bean
    public GroupedDynamicDataSource createDataSource() {
        return create();
    }

    @Bean
    public Map<String, List<MasterSlaveDataSource>> getAllDataSources() {
        return this.allDataSources;
    }

    /**
     * 数据源配置刷新处理
     *
     * @return
     */
    @Bean
    public ConfigReloadListener dynamicDataSourceRefresher(GroupedDynamicDataSource dataSource) {
        return new ConfigReloadListener() {

            private static final int MAX_DELAY_SIZE = 20;

            private ConcurrentLinkedQueue<DataSource> delayQueue = new ConcurrentLinkedQueue<>();

            @Override
            protected boolean shouldRefresh(Set<String> files) {
                return files.contains(getFileName());
            }

            @Override
            protected void doRefresh(Set<String> files) {
                // 关闭延迟处理的DataSource
                closeDelayDataSource();

                // 备份原dataSource
                Map<String, List<MasterSlaveDataSource>> oldDataSources = new HashMap<>();
                oldDataSources.putAll(getAllDataSources());
                allDataSources.clear();

                try {
                    // 创建新的dataSource
                    log.info("recreating datasources");
                    Map<Object, Object> dataSourceMap = createTargetDataSources();
                    dataSource.setTargetDataSources(dataSourceMap);
                    dataSource.afterPropertiesSet();

                    // 关闭旧的dataSource
                    log.info("closing old datasources");
                    oldDataSources.forEach((groupName, masterSlaveDataSources) -> masterSlaveDataSources.forEach((masterSlaveDataSource) -> {
                        DataSource master = masterSlaveDataSource.getMaster();
                        closeDataSourceIfNoActiveConnection(master);
                        Map<String, DataSource> slaves = masterSlaveDataSource.getSlaves();
                        slaves.forEach((dataSourceName, slave) -> closeDataSourceIfNoActiveConnection(slave));
                    }));
                } catch (DataSourceCreateException e) {
                    // 创建新数据源失败，继续使用旧数据源
                    // 如果新数据源处理已经创建并初始化的状态，需要关闭
                    allDataSources.putAll(oldDataSources);
                    closeInitedDataSources(e.getDataSources());
                    log.error("重新创建数据源失败", e);
                } catch (Exception e) {
                    log.error("重新创建数据源失败", e);
                }
            }

            private void closeInitedDataSources(List<DataSource> dataSources) {
                if (dataSources != null) {
                    dataSources.forEach(dataSource -> {
                        if (dataSource instanceof DruidDataSource) {
                            DruidDataSource druidDataSource = (DruidDataSource) dataSource;
                            if (druidDataSource.isInited()) {
                                tryCloseDruidDataSource(druidDataSource);
                            }
                        }
                    });
                }
            }

            private void closeDelayDataSource() {
                List<DataSource> escapedList = new ArrayList<>();
                while (!delayQueue.isEmpty()) {
                    DataSource dataSource = delayQueue.poll();
                    if (dataSource instanceof DruidDataSource) {
                        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
                        if (druidDataSource.getActiveCount() <= 0) {
                            tryCloseDruidDataSource(druidDataSource);
                        } else {
                            escapedList.add(dataSource);
                        }
                    }
                }
                delayQueue.addAll(escapedList);
            }

            private void closeDataSourceIfNoActiveConnection(DataSource dataSource) {
                if (dataSource instanceof DruidDataSource) {
                    DruidDataSource druidDataSource = (DruidDataSource) dataSource;
                    if (druidDataSource.getActiveCount() > 0 && delayQueue.size() < MAX_DELAY_SIZE) {
                        delayQueue.add(druidDataSource);
                    } else {
                        tryCloseDruidDataSource(druidDataSource);
                    }
                }
            }

            private void tryCloseDruidDataSource(DruidDataSource druidDataSource) {
                try {
                    druidDataSource.setBreakAfterAcquireFailure(true);
                    druidDataSource.close();
                } catch (Exception e) {
                    log.error("close druidDataSource error", e);
                }
            }

            private String getFileName() {
                return ("mysql-" + environment.getProperty("spring.profiles.active") + ".yml");
            }

        };
    }
}
