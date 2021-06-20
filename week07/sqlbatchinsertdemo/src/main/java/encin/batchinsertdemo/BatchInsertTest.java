package encin.batchinsertdemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Jammy
 * @create 2021-06-20
 */
public class BatchInsertTest {

    public static void main(final String[] args) throws SQLException {

        final JdbcUtil jdbcUtil = new JdbcUtil();
        final HikariDataSource hikariDS = jdbcUtil.getHikariDS();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = hikariDS.getConnection();
            connection.setAutoCommit(false);

            final String createSql = "insert into order_info (id, user_id, create_time) values(?,?,?)";
            ps = connection.prepareStatement(createSql);
            final Long start = System.currentTimeMillis();
            int k = 1;

            for (Integer i = 1; i <= 1000000; i++) {
                ps.setInt(1, i);
                ps.setInt(2, (2));
                ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                ps.addBatch();
                
                //每10000条数据执行一次
                if (i % 10000 == 0) {

                    ps.executeBatch();
                    connection.commit();
                    ps.clearBatch();

                    System.out.println("execute-----" + k++ + "times");
                }
            }

            final Long end = System.currentTimeMillis();
            
            //5145ms
            System.out.println("耗时(ms)：" + (end - start));
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            ps.close();
            connection.close();
        }
    }
}
