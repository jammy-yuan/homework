package jammy.ss.service;

import javax.sql.DataSource;

import jammy.ss.entity.UserInfo;

public interface OrderService {

    public Boolean addOrder(DataSource dataSource, UserInfo user);

    public UserInfo getOrderById(DataSource dataSource, String orderId);
}
