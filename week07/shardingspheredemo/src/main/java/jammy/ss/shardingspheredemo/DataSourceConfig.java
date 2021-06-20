package jammy.ss.shardingspheredemo;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    public DataSource db0() {
        final HikariConfig configuration = new HikariConfig();
        configuration.setDriverClassName("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3316/db");
        configuration.setUsername("root");
        configuration.setPassword("123456");
        return new HikariDataSource(configuration);
    }

    public DataSource db1() {
        final HikariConfig configuration = new HikariConfig();
        configuration.setDriverClassName("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3326/db");
        configuration.setUsername("root");
        configuration.setPassword("123456");
        return new HikariDataSource(configuration);
    }

    public ShardingTableRuleConfiguration orderTableRuleConfig() {
        final ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration("order_info", "db${0..1}.order_info${0..1}");
        orderTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("user_id", "dbShardingAlgorithm"));
        orderTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("id", "tableShardingAlgorithm"));
        return orderTableRuleConfig;
    }

    public ShardingRuleConfiguration shardingRuleConfig() {
        final ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTables().add(orderTableRuleConfig());
        // 配置分库算法
        final Properties dbShardingAlgorithmrProps = new Properties();
        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "db${user_id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));

        // 配置分表算法
        final Properties tableShardingAlgorithmrProps = new Properties();
        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "user_info{id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm",
                                                       new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));

        return shardingRuleConfig;
    }

    @Bean
    public DataSource shardingSphereDb() throws SQLException {
        final Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("db0", db0());
        dataSourceMap.put("db1", db1());
        return ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(shardingRuleConfig()), new Properties());
    }
}
