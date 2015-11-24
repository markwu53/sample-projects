package com.markwu.spring.boot;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

        @Autowired private ApplicationContext applicationContext;
        @Value("${activeDatabase:mysql}") private String activeDatabase;
        @Value("${datasource.error.message:DataSource error}") private String dataSourceErrorMessage;

        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
                System.out.println("+++++++++++++++++++++Initializing Web App Context++++++++++++++++");
                return builder.sources(Application.class);
        }

        @Bean
        public DataSource dataSource() {
                System.out.println("+++++++++++++++++++++Initializing data source++++++++++++++++");
                DataSource dataSource = null;
                if ("hive".equalsIgnoreCase(activeDatabase)) {
                        dataSource = hiveDataSource();
                } else if ("mysql".equalsIgnoreCase(activeDatabase)) {
                        dataSource = mysqlDataSource();
                }
                Assert.notNull(dataSource, dataSourceErrorMessage);
                return dataSource;
        }

        public DataSource mysqlDataSource() {
                System.out.println("+++++++++++++++++++++Initializing mysql data source++++++++++++++++");
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setDriverClassName(applicationContext.getEnvironment().getProperty("mysql.jdbc.driver"));
                dataSource.setUrl(applicationContext.getEnvironment().getProperty("mysql.jdbc.url"));
                dataSource.setUsername(applicationContext.getEnvironment().getProperty("mysql.jdbc.username"));
                dataSource.setPassword(applicationContext.getEnvironment().getProperty("mysql.jdbc.password"));
                //verify and initialize dataSource
                new JdbcTemplate(dataSource, false);
                return dataSource;
        }

        public DataSource hiveDataSource() {
                System.out.println("+++++++++++++++++++++Initializing hive data source++++++++++++++++");
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setDriverClassName(applicationContext.getEnvironment().getProperty("hive.jdbc.driver"));
                dataSource.setUrl(applicationContext.getEnvironment().getProperty("hive.jdbc.url"));
                dataSource.setUsername(applicationContext.getEnvironment().getProperty("hive.jdbc.username"));
                dataSource.setPassword(applicationContext.getEnvironment().getProperty("hive.jdbc.password"));
                //verify and initialize dataSource
                new JdbcTemplate(dataSource, false);
                return dataSource;
        }

}
