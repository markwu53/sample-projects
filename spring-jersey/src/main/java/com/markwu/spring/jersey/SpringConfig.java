package com.markwu.spring.jersey;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

        @Bean
        public DataSource phoenixDataSource() {
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setDriverClassName("org.apache.phoenix.jdbc.PhoenixDriver");
                dataSource.setUrl(ConnectionStrings.PHOENIX);
                return dataSource;
        }

        @Bean
        public Utils utils() {
                return new Utils();
        }

}
