package com.markwu.spring.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration.Dynamic;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan("com.markwu.spring.web")
public class MyWebApp extends WebMvcConfigurerAdapter implements WebApplicationInitializer {

        @Override
        public void onStartup(ServletContext container) {
                AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
                appContext.register(MyWebApp.class);
                container.addListener(new ContextLoaderListener(appContext));
                Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(appContext));
                dispatcher.setLoadOnStartup(1);
                dispatcher.addMapping("/");
        }

        @Bean
        public InternalResourceViewResolver viewResolver() {
                return new InternalResourceViewResolver();
        }

        @Bean
        public DataSource mysqlDataSource() {
                try {
                        Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                }
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setUrl("jdbc:mysql://sandbox.hortonworks.com:3306/information_schema");
                dataSource.setUsername("hive");
                dataSource.setPassword("hive");
                return dataSource;
        }

}
