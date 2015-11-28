package com.my.leaflet.prepare;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class PhoenixTable {

        public static void main(String[] args) {
                SpringApplication app = new SpringApplication(PhoenixTable.class);
                app.setWebEnvironment(false);
                ConfigurableApplicationContext ctx = app.run(args);
                String driverClassName = ctx.getEnvironment().getProperty("phoenix.jdbc.driver");
                String url = ctx.getEnvironment().getProperty("phoenix.jdbc.url");
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setDriverClassName(driverClassName);
                dataSource.setUrl(url);
                JdbcTemplate jdbc = new JdbcTemplate(dataSource);
                jdbc.execute(ctx.getEnvironment().getProperty("sql.create_trip_table"));
        }

}
