package encin.batchinsertdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Jammy
 * @create 2021-06-20
 */
public class JdbcUtil {
    public static String url = "jdbc:mysql://localhost:3306/geek_study?useSSL=false&rewriteBatchedStatements=true";
    public static String username = "root";
    public static String password = "root";

    public Connection getConn() throws SQLException {
        Connection conn = null;
        final Properties connectionProps = new Properties();
        connectionProps.put("user", username);
        connectionProps.put("password", password);
        conn = DriverManager.getConnection(url, connectionProps);
        return conn;
    }

    public HikariDataSource getHikariDS() throws SQLException {
        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(4);
        return new HikariDataSource(config);
    }

}
