package io.github.vibrantbyte.storage.datasource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author vibrant byte
 */
public class MasterSlaveDataSource {

    private String name;

    private DataSource master;

    private String masterName;

    private Map<String, DataSource> slaves;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MasterSlaveDataSource() {
    }

    public DataSource getMaster() {
        return master;
    }

    public MasterSlaveDataSource setMaster(DataSource master) {
        this.master = master;
        return this;
    }

    public String getMasterName() {
        return masterName;
    }

    public MasterSlaveDataSource setMasterName(String masterName) {
        this.masterName = masterName;
        return this;
    }

    public Map<String, DataSource> getSlaves() {
        return slaves;
    }

    public MasterSlaveDataSource setSlaves(Map<String, DataSource> slaves) {
        this.slaves = slaves;
        return this;
    }
}
