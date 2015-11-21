package com.markwu.spring.jersey;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Path("/phoenix")
public class PhoenixHandler {

        @Path("/table")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public String table() {
                DataSource dataSource = SpringJerseyApp.appContext.getBean("phoenixDataSource", DataSource.class);
                Utils utils = SpringJerseyApp.appContext.getBean("utils", Utils.class);
                JdbcTemplate jdbc = new JdbcTemplate(dataSource);
                String sql = String.format("select distinct table_schem, table_name from system.catalog");
                List<String> header = new ArrayList<String>();
                List<List<String>> rows = utils.myQuery(jdbc, sql, header);
                System.out.println(header);
                for (List<String> row: rows) System.out.println(row);
                return new Gson().toJson(rows, new TypeToken<List<List<String>>>(){}.getType());
        }

}
