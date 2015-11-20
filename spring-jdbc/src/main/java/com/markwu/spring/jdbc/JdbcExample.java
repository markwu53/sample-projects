package com.markwu.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcExample implements Runnable {

        private static AnnotationConfigApplicationContext appContext;

        public static void main(String[] args) throws Exception {
                appContext = new AnnotationConfigApplicationContext(JdbcExample.class);
                appContext.getBean(JdbcExample.class).run();
                appContext.close();
        }

        public void run() {
                try {
                        go();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        @Bean
        public DataSource mysqlWufamilyDataSource() {
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setUrl(ConnectionStrings.mysql_wufamily);
                dataSource.setUsername("dbuser");
                dataSource.setPassword("12345678");
                return dataSource;
        }

        @Bean
        public DataSource mysqlSandboxDataSource() {
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setUrl(ConnectionStrings.mysql_hdp_sandbox);
                dataSource.setUsername("hive");
                dataSource.setPassword("hive");
                return dataSource;
        }

        public void go() throws Exception {
                //DataSource dataSource = appContext.getBean("mysqlWufamilyDataSource", DataSource.class);
                DataSource dataSource = appContext.getBean("mysqlSandboxDataSource", DataSource.class);
                JdbcTemplate jdbc = new JdbcTemplate(dataSource);
                String sql = "select * from tables";
                List<String> rows = jdbc.query(sql, new RowMapper<String>() {
                        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                                int colCount = rs.getMetaData().getColumnCount();
                                StringBuilder row = new StringBuilder();
                                for (int col = 1; col <= colCount; col ++) {
                                        row.append(rs.getString(col));
                                        row.append(" | ");
                                }
                                return row.toString();
                        }
                });
                for (String row: rows) System.out.println(row);
        }

}
