package com.markwu.spring.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class JdbcExample {

        public static void main(String[] args) throws Exception {
                AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(JdbcExample.class);
                Connection conn = appContext.getBean("mysqlDataSource", DataSource.class).getConnection();
                String sql = "select * from TABLE_PARAMS";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        System.out.println(String.format("%s|%s|%s", rs.getString(1), rs.getString(2), rs.getString(3)));
                }
                rs.close();
                ps.close();
                conn.close();
                appContext.close();
        }

        @Bean
        public DataSource mysqlDataSource() {
                String url = "jdbc:mysql://sandbox.hortonworks.com:3306/hive";
                String user = "root";
                String pass = "hadoop";
                //DriverManagerDataSource dataSource = new DriverManagerDataSource(url, user, pass);
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setUrl(url);
                dataSource.setUsername(user);
                dataSource.setPassword(pass);
                return dataSource;
        }

}
