package io.github.vibrantbyte.storage.datasource;

/**
 * @author vibrant byte
 */
public interface IDynamicAutoConfiguration {

    /**
     * 创建数据源
     * @return
     */
    GroupedDynamicDataSource createDataSource();
}
