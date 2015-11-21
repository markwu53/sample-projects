package com.markwu.spring.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
//@ComponentScan("com.markwu.spring.web")
public class MyWebApp implements WebApplicationInitializer {

        @Override
        public void onStartup(ServletContext container) throws ServletException {
                AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
                context.register(MyWebApp.class);
                context.register(Utils.class, MysqlController.class);
                container.addListener(new ContextLoaderListener(context));
                Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(context));
                dispatcher.setLoadOnStartup(1);
                dispatcher.addMapping("/");
        }

        @Bean
        public InternalResourceViewResolver viewResolver() {
                return new InternalResourceViewResolver();
        }

        //@Bean
        public DataSource fiopsDataSource() {
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
                dataSource.setUrl(ConnectionStrings.ORACLE_FIOPS);
                dataSource.setUsername("buys");
                dataSource.setPassword("dataownr");
                return dataSource;
        }

        @Bean
        public DataSource mysqlDataSource() {
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setDriverClassName("com.mysql.jdbc.Driver");
                //dataSource.setUrl(ConnectionStrings.MYSQL_SANDBOX);
                //dataSource.setUsername("hive");
                //dataSource.setPassword("hive");
                dataSource.setUrl(ConnectionStrings.MYSQL_WUFAMILY);
                dataSource.setUsername("dbuser");
                dataSource.setPassword("12345678");
                return dataSource;
        }

        //@Bean
        public DataSource phoenixDataSource() {
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setDriverClassName("org.apache.phoenix.jdbc.PhoenixDriver");
                dataSource.setUrl(ConnectionStrings.PHOENIX);
                //dataSource.setUsername("buys");
                //dataSource.setPassword("dataownr");
                return dataSource;
        }

}
