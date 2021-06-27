package com.jammy.shardingSphereDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jammy.shardingsphere.config.SSConfig;

@SpringBootApplication
public class ShardingSphereDemoApplication {

	public static void main(final String[] args) throws SQLException {
        SpringApplication.run(ShardingSphereDemoApplication.class, args);
        insertSql();
        deleteSql();
        updateSql();
        selectSql();
    }
	
	public static void deleteSql() throws SQLException {
        final Connection connection = getDataSource().getConnection();
        connection.setAutoCommit(false);
        final String sql = "delete from t_order where user_id = ?;";
        final PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, 7);
        ps.execute();
        connection.commit();
        ps.close();
        connection.close();
    }

    public static void updateSql() throws SQLException {
        final Connection connection = getDataSource().getConnection();
        connection.setAutoCommit(false);
        final String sql = "update t_order set status = ? where user_id = ?;";
        final PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "FAIL");
        ps.setInt(2, 9);
        ps.execute();
        connection.commit();
        ps.close();
        connection.close();
    }
    
    public static void insertSql() throws SQLException {
        final Connection connection = getDataSource().getConnection();
        connection.setAutoCommit(false);
        final String sql = "insert into t_order (user_id, order_id) values(?, ?);";
        final PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                ps.setInt(1,i);
                ps.setInt(2,j);
                ps.addBatch();
            }
            ps.executeBatch();
        }
        connection.commit();
        ps.close();
        connection.close();
    }

    public static void selectSql() throws SQLException {
        final Connection connection = getDataSource().getConnection();
        final String sql = "select user_id, order_id from t_order where user_id = 5 order by order_id";
        final ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
        while (resultSet.next()) {
            System.out.println("user_id:" + resultSet.getInt(1) + " - order_id:" + resultSet.getInt(2));
        }
        connection.close();
    }

    private static DataSource getDataSource() throws SQLException {
        final SSConfig shardingSphereConfig = new SSConfig();
        final DataSource dataSource = shardingSphereConfig.shardingSphereDb();
        return dataSource;
    }

}
