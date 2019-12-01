package io.github.vibrantbyte.storage.datasource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vibrant byte
 */
public class DataSourceCreateException extends RuntimeException {

    private List<DataSource> dataSources = new ArrayList<DataSource>();

    public DataSourceCreateException() {
        super();
    }

    public DataSourceCreateException(String message) {
        super(message);
    }

    public DataSourceCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataSourceCreateException(Throwable cause) {
        super(cause);
    }

    public DataSourceCreateException(String message, Throwable cause, List<DataSource> dataSources) {
        super(message, cause);
        if (null != dataSources) {
            this.dataSources = dataSources;
        }
    }

    public DataSourceCreateException(String message, List<DataSource> dataSources) {
        super(message);
        if (null != dataSources) {
            this.dataSources = dataSources;
        }
    }

    public DataSourceCreateException(Throwable cause, List<DataSource> dataSources) {
        super(cause);
        if (null != dataSources) {
            this.dataSources = dataSources;
        }
    }

    public List<DataSource> getDataSources() {
        return dataSources;
    }
}
