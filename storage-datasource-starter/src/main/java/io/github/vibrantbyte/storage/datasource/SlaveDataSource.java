package io.github.vibrantbyte.storage.datasource;

import javax.sql.DataSource;

/**
 * @author  vibrant byte
 */
public class SlaveDataSource {

    private String name;

    private int weight;

    private DataSource dataSource;

    public SlaveDataSource(String name, DataSource dataSource) {
        this.name = name;
        this.dataSource = dataSource;
    }

    public SlaveDataSource(String name, int weight, DataSource dataSource) {
        this.name = name;
        this.weight = weight;
        this.dataSource = dataSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
