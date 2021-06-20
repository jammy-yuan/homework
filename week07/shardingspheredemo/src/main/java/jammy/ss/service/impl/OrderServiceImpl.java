package jammy.ss.service.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import jammy.ss.entity.UserInfo;
import jammy.ss.service.OrderService;

/**
 * @author Encin.Li
 * @create 2021-03-07
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Boolean addOrder(final DataSource dataSource, final UserInfo user) {
        final String sql = String.format("insert into user_info (id,user_name,login_id,pwd)values(%d,%d,%d,%d);", 
        		user.getId(), user.getUserName(), user.getLoginId(), user.getPwd());
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.prepareStatement(sql).execute();
        } catch (final SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public UserInfo getOrderById(final DataSource dataSource, final String orderId) {
        Connection connection = null;
        final String sql = "select * from user_info where id = '" + orderId + "';";

        try {
            connection = dataSource.getConnection();
            final ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            return new UserInfo(id, "test", "test", "test");
        } catch (final SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
