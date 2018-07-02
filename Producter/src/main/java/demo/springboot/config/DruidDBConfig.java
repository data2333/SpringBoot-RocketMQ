package demo.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sun on 18-6-26.
 */
@Configuration
public class DruidDBConfig {

        //  private Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);

        @Value("${spring.datasource.url}")
        private String dbUrl;

        @Value("${spring.datasource.username}")
        private String username;

        @Value("${spring.datasource.password}")
        private String password;

        @Value("${spring.datasource.driverClassName}")
        private String driverClassName;

        @Value("${spring.datasource.initialSize}")
        private int initialSize;

        @Value("${spring.datasource.minIdle}")
        private int minIdle;

        @Value("${spring.datasource.maxActive}")
        private int maxActive;

        @Value("${spring.datasource.maxWait}")
        private int maxWait;

        @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
        private int timeBetweenEvictionRunsMillis;

        @Value("${spring.datasource.minEvictableIdleTimeMillis}")
        private int minEvictableIdleTimeMillis;

        @Value("${spring.datasource.validationQuery}")
        private String validationQuery;

        @Value("${spring.datasource.testWhileIdle}")
        private boolean testWhileIdle;

        @Value("${spring.datasource.testOnBorrow}")
        private boolean testOnBorrow;

        @Value("${spring.datasource.testOnReturn}")
        private boolean testOnReturn;

        @Value("${spring.datasource.poolPreparedStatements}")
        private boolean poolPreparedStatements;

        @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
        private int maxPoolPreparedStatementPerConnectionSize;

        @Value("${spring.datasource.filters}")
        private String filters;

        @Value("{spring.datasource.connectionProperties}")
        private String connectionProperties;
    @Bean
    public DataSource getDataSource() {
        return buildDataSource();
    }
    private DataSource buildDataSource() {
        //设置分库映射
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        //添加两个数据库ds_0,ds_1到map里
        dataSourceMap.put("ds_0", dataSource("ds_0"));
        dataSourceMap.put("ds_1", dataSource("ds_1"));
        dataSourceMap.put("test", dataSource("test"));
        //设置默认db为ds_0，也就是为那些没有配置分库分表策略的指定的默认库
        //如果只有一个库，也就是不需要分库的话，map里只放一个映射就行了，只有一个库时不需要指定默认库，但2个及以上时必须指定默认库，否则那些没有配置策略的表将无法操作数据
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap
                , "test");
        //设置分表映射，将t_order_0和t_order_1两个实际的表映射到t_order逻辑表
        //0和1两个表是真实的表，t_order是个虚拟不存在的表，只是供使用。如查询所有数据就是select * from t_order就能查完0和1表的
        TableRule orderTableRule = TableRule.builder("t_order")
                .actualTables(Arrays.asList("t_order_0", "t_order_1"))
                .dataSourceRule(dataSourceRule)
                .build();

        //具体分库分表策略，按什么规则来分
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(orderTableRule))
                .databaseShardingStrategy(new
                        DatabaseShardingStrategy("user_id", new ModuloDatabaseShardingAlgorithm()))
                .tableShardingStrategy(new
                        TableShardingStrategy("order_id", new ModuloTableShardingAlgorithm())).
                        build();
        DataSource dataSource =
                ShardingDataSourceFactory.createDataSource(shardingRule);

        return dataSource;
    }
//        @Bean // 声明其为Bean实例
//        @Primary // 在同样的DataSource中，首先使用被标注的DataSource
        public DataSource dataSource(String dbName) {
            DruidDataSource datasource = new DruidDataSource();

            datasource.setUrl(this.dbUrl+dbName);
            datasource.setUsername(username);
            datasource.setPassword(password);
            datasource.setDriverClassName(driverClassName);
            // configuration
            datasource.setInitialSize(initialSize);
            datasource.setMinIdle(minIdle);
            datasource.setMaxActive(maxActive);
            datasource.setMaxWait(maxWait);
            datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            datasource.setValidationQuery(validationQuery);
            datasource.setTestWhileIdle(testWhileIdle);
            datasource.setTestOnBorrow(testOnBorrow);
            datasource.setTestOnReturn(testOnReturn);
            datasource.setPoolPreparedStatements(poolPreparedStatements);
            datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
            try {
                datasource.setFilters(filters);
            } catch (SQLException e) {
            }
            datasource.setConnectionProperties(connectionProperties);
            return datasource;
        }
}
