package io.github.vibrantbyte.storage.datasource.test;

import io.github.vibrantbyte.storage.datasource.DynamicDataSourceAutoConfiguration;
import io.github.vibrantbyte.storage.datasource.DynamicDataSourceContext;
import io.github.vibrantbyte.storage.datasource.MasterSlaveDataSource;
import io.github.vibrantbyte.storage.datasource.sharding.ShardingJdbcConfig;
import io.shardingjdbc.core.api.HintManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {DynamicDataSourceAutoConfiguration.class}
)
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class
})
public class DataSourceTest {

    /**
     * 触发创建动态数据源
     */
    @Autowired
    private DataSource dataSource;

    @Autowired
    private ShardingJdbcConfig shardingJdbcConfig;

    @Autowired
    private Map<String, List<MasterSlaveDataSource>> allDataSources;

    /**
     * 执行单测前，需要先手动执行data.sql创建相关数据库和初始数据
     */

    @Test
    public void testDefaultDataSource() {
        DynamicDataSourceContext.setCurrentGroupName("default");
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.query("SELECT * FROM t_order WHERE order_id = 1000 ", row -> {
            int orderId = row.getInt("order_id");
            int userId = row.getInt("user_id");
            Assert.assertEquals(orderId,1000);
            Assert.assertEquals(userId,2000);
        });
    }

    @Test
    public void testOneMasterDataSource() {
        DynamicDataSourceContext.setCurrentGroupName("one_master");
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.query("SELECT * FROM t_order_item WHERE order_id = 1000 ", row -> {
            int itemId = row.getInt("item_id");
            int userId = row.getInt("user_id");
            Assert.assertEquals(itemId,3000);
            Assert.assertEquals(userId,2000);
        });
    }

    @Test
    public void testOneMasterSlaves() {
        DynamicDataSourceContext.setCurrentGroupName("one_master_mutiple_slave");
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("INSERT INTO t_order(order_id, user_id) VALUES(-1000, -2222)");
        template.query("SELECT * FROM t_order WHERE order_id = -1000", row -> {
            int orderId = row.getInt("order_id");
            int userId = row.getInt("user_id");
            Assert.assertEquals(userId,-2222);
        });

        HintManager hintManager = HintManager.getInstance();
        hintManager.setMasterRouteOnly();


        template.query("SELECT * FROM t_order WHERE order_id = -1000", row -> {
            int orderId = row.getInt("order_id");
            int userId = row.getInt("user_id");
            Assert.assertEquals(userId,-2222);
        });

        hintManager.close();
        template.query("SELECT * FROM t_order WHERE order_id = -1000", row -> {
            int orderId = row.getInt("order_id");
            int userId = row.getInt("user_id");
            Assert.assertEquals(userId,0);
        });
    }

    @Test
    @Transactional
    public void testMultipleMasterSlaves() {
        /**
         * 测试拆库拆表
         */
        DynamicDataSourceContext.setCurrentGroupName("mutiple_master_slaves");
        JdbcTemplate template = new JdbcTemplate(dataSource);
        /**
         * 测试写入数据
         */
        int result = template.update("INSERT INTO t_order(order_id, user_id) VALUES(90008, 92222)");
        Assert.assertEquals(result,1);

        final boolean[] haveResult = {false};
        template.query("SELECT * FROM t_order WHERE order_id = 90008 and user_id = 92222", row -> {
            int userId = row.getInt("user_id");
            if (userId == 92222) {
                haveResult[0] = true;
            }
        });

        /**
         * 模拟从库同步
         */
        List<MasterSlaveDataSource> msList = allDataSources.get("mutiple_master_slaves");
        Assert.assertNotNull(msList);
        Assert.assertEquals(msList.size(),2);

        MasterSlaveDataSource ms = msList.get(0);
        DataSource slave = ms.getSlaves().get("mutiple_master_slaves_group_0_slave_0");
        template = new JdbcTemplate(slave);
        result = template.update("INSERT INTO t_order_1(order_id, user_id) VALUES(90008, 92222)");


        /**
         * 模拟从从库读取
         */
        DynamicDataSourceContext.setCurrentGroupName("mutiple_master_slaves");
        template = new JdbcTemplate(dataSource);
        Object rs = template.queryForObject("SELECT * FROM t_order WHERE order_id = 90008 and user_id = 92222", (RowMapper<Object>) (rs1, rowNum) -> rs1.getInt("user_id"));
        Assert.assertEquals(rs,92222);
    }

    @Test
    @Transactional
    public void testMultiTransactional() {
        DynamicDataSourceContext.setCurrentGroupName("default");
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("INSERT INTO t_order(order_id, user_id) VALUES(2001, 2001)");
        template.query("SELECT * FROM t_order WHERE order_id = 2001", row -> {
            int orderId = row.getInt("order_id");
            int userId = row.getInt("user_id");
            System.out.println("default: orderId="+orderId);
            Assert.assertEquals(userId,2001);
        });
        DynamicDataSourceContext.setCurrentGroupName("one_master_mutiple_slave");
        JdbcTemplate template2 = new JdbcTemplate(dataSource);
        template2.update("INSERT INTO t_order(order_id, user_id) VALUES(2011, 2011)");
        template2.query("SELECT * FROM t_order WHERE order_id = 2011", row -> {
            int orderId = row.getInt("order_id");
            int userId = row.getInt("user_id");
            System.out.println("one_master_mutiple_slave: orderId="+orderId);
            Assert.assertEquals(userId,2011);
        });
    }
}
