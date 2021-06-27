package com.atomikosxa.xademo;

import java.util.Properties;

import com.atomikos.jdbc.AtomikosDataSourceBean;

public class XaConfig {
    
    public static AtomikosDataSourceBean createAtomikosDataSourceBean(final String schemaName, final String dsName) {
        Properties p = new Properties();
        p.setProperty("url", "jdbc:mysql://localhost:3306/" + schemaName);
        p.setProperty("user", "root");
        p.setProperty("password", "root");
   
        // 使用AtomikosDataSourceBean封装com.mysql.jdbc.jdbc2.optional.MysqlXADataSource
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName(dsName);
        ds.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        ds.setXaProperties(p);
        return ds;
     }

}
