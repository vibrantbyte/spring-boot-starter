package io.github.vibrantbyte.storage.datasource;

import io.github.vibrantbyte.storage.datasource.yml.YamlPropertyLoaderFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vibrant byte
 */
@Configuration
@ConfigurationProperties(
        prefix = "mysql"
)
@PropertySource(
        value = {"file:${spring.config.location}/mysql/mysql-${spring.profiles.active}.yml", "classpath:mysql/mysql-${spring.profiles.active}.yml"},
        ignoreResourceNotFound = true,
        factory = YamlPropertyLoaderFactory.class
)
public class DynamicDataSourceConfig {

    public DynamicDataSourceConfig() {
    }

    private List<DataSourceConfigGroup> groups;

    public List<DataSourceConfigGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<DataSourceConfigGroup> groups) {
        this.groups = groups;
    }

    public static class DataSourceConfig {

        private String name;

        private String url;

        private String username;

        private String password;

        public int getInitialSize() {
            return initialSize;
        }

        public void setInitialSize(int initialSize) {
            this.initialSize = initialSize;
        }

        public int getMaxActive() {
            return maxActive;
        }

        public void setMaxActive(int maxActive) {
            this.maxActive = maxActive;
        }

        public int getMinIdle() {
            return minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public int getMaxWait() {
            return maxWait;
        }

        public void setMaxWait(int maxWait) {
            this.maxWait = maxWait;
        }

        public boolean isPoolPreparedStatements() {
            return poolPreparedStatements;
        }

        public void setPoolPreparedStatements(boolean poolPreparedStatements) {
            this.poolPreparedStatements = poolPreparedStatements;
        }

        public int getMaxPoolPreparedStatementPerConnectionSize() {
            return maxPoolPreparedStatementPerConnectionSize;
        }

        public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
            this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
        }

        public String getValidationQuery() {
            return validationQuery;
        }

        public void setValidationQuery(String validationQuery) {
            this.validationQuery = validationQuery;
        }

        public boolean isTestOnBorrow() {
            return testOnBorrow;
        }

        public void setTestOnBorrow(boolean testOnBorrow) {
            this.testOnBorrow = testOnBorrow;
        }

        public boolean isTestOnReturn() {
            return testOnReturn;
        }

        public void setTestOnReturn(boolean testOnReturn) {
            this.testOnReturn = testOnReturn;
        }

        public boolean isTestWhileIdle() {
            return testWhileIdle;
        }

        public void setTestWhileIdle(boolean testWhileIdle) {
            this.testWhileIdle = testWhileIdle;
        }

        public int getTimeBetweenEvictionRunsMillis() {
            return timeBetweenEvictionRunsMillis;
        }

        public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
            this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
        }

        public int getMinEvictableIdleTimeMillis() {
            return minEvictableIdleTimeMillis;
        }

        public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
            this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
        }

        public String getFilters() {
            return filters;
        }

        public void setFilters(String filters) {
            this.filters = filters;
        }

        private int initialSize;

        private int  maxActive;

        private int minIdle;

        private int maxWait;

        private boolean poolPreparedStatements;

        private int maxPoolPreparedStatementPerConnectionSize;

        private String validationQuery;

        private boolean testOnBorrow;

        private boolean testOnReturn;

        private boolean testWhileIdle;

        private int timeBetweenEvictionRunsMillis;

        private int minEvictableIdleTimeMillis;

        private String filters;

        private int weight;

        private int transactionTimeout;

        private String mergeSql = "false";
        private String slowSqlMills = "3000";
        private String useGlobalDataSourceStat = "false";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getTransactionTimeout() {
            return transactionTimeout;
        }

        public void setTransactionTimeout(int transactionTimeout) {
            this.transactionTimeout = transactionTimeout;
        }

        public String getMergeSql() {
            return mergeSql;
        }

        public void setMergeSql(String mergeSql) {
            this.mergeSql = mergeSql;
        }

        public String getSlowSqlMills() {
            return slowSqlMills;
        }

        public void setSlowSqlMills(String slowSqlMills) {
            this.slowSqlMills = slowSqlMills;
        }

        public String getUseGlobalDataSourceStat() {
            return useGlobalDataSourceStat;
        }

        public void setUseGlobalDataSourceStat(String useGlobalDataSourceStat) {
            this.useGlobalDataSourceStat = useGlobalDataSourceStat;
        }
    }

    public static class MasterSlaveDataSourceConfig {

        private String name;

        private DataSourceConfig master;

        private List<DataSourceConfig> slaves = new ArrayList<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public DataSourceConfig getMaster() {
            return master;
        }

        public void setMaster(DataSourceConfig master) {
            this.master = master;
        }

        public List<DataSourceConfig> getSlaves() {
            return slaves;
        }

        public void setSlaves(List<DataSourceConfig> slaves) {
            this.slaves = slaves;
        }

        public boolean isMasterSlave() {
            if ((null != master && null != slaves && slaves.size() > 0))
                return true;
            else
                return false;
        }
    }

    public static class DataSourceConfigGroup {

        /**
         * datasource的组名
         */
        private String name;

        /**
         * 主库
         */
        private DataSourceConfig master;

        /**
         * 从库
         */
        private List<DataSourceConfig> slaves = new ArrayList<>();

        /**
         * 主动库集合，用于配合Sharding JDBC时的分库时使用
         */
        private List<MasterSlaveDataSourceConfig> masterSlaves;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public DataSourceConfig getMaster() {
            return master;
        }

        public void setMaster(DataSourceConfig master) {
            this.master = master;
        }

        public List<DataSourceConfig> getSlaves() {
            return slaves;
        }

        public void setSlaves(List<DataSourceConfig> slaves) {
            this.slaves = slaves;
        }

        public List<MasterSlaveDataSourceConfig> getMasterSlaves() {
            return masterSlaves;
        }

        public void setMasterSlaves(List<MasterSlaveDataSourceConfig> masterSlaves) {
            this.masterSlaves = masterSlaves;
        }

        public boolean isMasterSlave() {
            if ((null != master && null != slaves && slaves.size() > 0) || (null != masterSlaves && masterSlaves.size() > 0)) {
                return true;
            }

            return false;
        }
    }

}