package com.atomikosxa.xademo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.jdbc.AtomikosDataSourceBean;

@SpringBootApplication
public class XademoApplication {

	public static void main(String[] args) throws Exception {
		AtomikosDataSourceBean ds1 = XaConfig.createAtomikosDataSourceBean("demo_ds_0", "ds0");
	      AtomikosDataSourceBean ds2 = XaConfig.createAtomikosDataSourceBean("demo_ds_1", "ds1");
	 
	      Connection conn1 = null;
	      Connection conn2 = null;
	      PreparedStatement ps1 = null;
	      PreparedStatement ps2 = null;
	 
	      UserTransaction userTransaction = new UserTransactionImp();
	      try {
	         // 开启事务
	         userTransaction.begin();
	 
	         // 执行db1上的sql
	         conn1 = ds1.getConnection();
	         ps1 = conn1.prepareStatement("INSERT INTO t_order_0 (user_id) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
	         for (int i = 256; i < 261; i++) {
	        	 ps1.setInt(1, i);
	        	 ps1.addBatch();
	            }
	         ps1.executeBatch();
	         ResultSet generatedKeys = ps1.getGeneratedKeys();
	         int orderId = -1;
	         while (generatedKeys.next()) {
	        	 orderId = generatedKeys.getInt(1);// 获得自动生成的orderId
	        	 System.out.println("插入了一条数据， order_id为： " + orderId);
	         }
	 
	         // 模拟异常 ，直接进入catch代码块，2个都不会提交
//	        int j = 1/0;
	 
	         // 执行db2上的sql
	         conn2 = ds2.getConnection();
	         ps2 = conn2.prepareStatement("INSERT INTO t_order_0 (user_id) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
	         for (int i = 261; i < 271; i++) {
	        	 ps2.setInt(1, i);
	        	 ps2.addBatch();
	            }
	         ps2.executeBatch();
	 
	         // 两阶段提交
	         userTransaction.commit();
	      } catch (Exception e) {
	         try {
	            e.printStackTrace();
	            System.out.println("产生异常，事务回滚！");
	            //如果捕获了任何exception，则整个xa事务rollback
	            userTransaction.rollback();
	         } catch (SystemException e1) {
	            e1.printStackTrace();
	         }
	      } finally {
            ps1.close();
            ps2.close();
            conn1.close();
            conn2.close();
            ds1.close();
            ds2.close();
	      }
	}

}
