package io.github.vibrantbyte.storage.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;


/**
 * @author vibrant byte
 */
public class GroupedDynamicDataSource extends AbstractRoutingDataSource {

    private static final String DEFAULT_GROUP = "default";

    @Override
    protected Object determineCurrentLookupKey() {
        String name = DynamicDataSourceContext.getCurrentGroupName();
        if (null != name && name.length() > 0) {
            return name;
        } else {
            return DEFAULT_GROUP;
        }
    }

    public DataSource getDataSource(String groupName) {
        return this.resolveSpecifiedDataSource(groupName);
    }

}
