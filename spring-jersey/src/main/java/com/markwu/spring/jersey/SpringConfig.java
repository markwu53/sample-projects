package com.markwu.spring.jersey;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan("com.markwu.spring.jersey")
public class SpringConfig extends WebMvcConfigurerAdapter {

        @Bean
        public DataSource phoenixDataSource() {
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setDriverClassName("org.apache.phoenix.jdbc.PhoenixDriver");
                dataSource.setUrl(ConnectionStrings.PHOENIX);
                return dataSource;
        }

        @Bean
        public InternalResourceViewResolver viewResolver() {
                return new InternalResourceViewResolver();
        }

}
