package com.markwu.spring.boot;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

@RestController
public class MyController {

        @Autowired private DataSource dataSource;

        @RequestMapping("/drivers")
        public String drivers() {
                JdbcTemplate jdbc = new JdbcTemplate(dataSource);
                final List<String> header = new ArrayList<String>();
                List<List<String>> rows = jdbc.query("select * from drivers limit 20", new RowMapper<List<String>>() {
                        @Override
                        public List<String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                                if (header.isEmpty()) {
                                        ResultSetMetaData meta = rs.getMetaData();
                                        int columnCount = meta.getColumnCount();
                                        for (int col = 1; col <= columnCount; col ++) {
                                                header.add(meta.getColumnName(col));
                                        }
                                }
                                List<String> row = new ArrayList<String>();
                                for (int col = 1; col <= header.size(); col ++) {
                                        row.add(rs.getString(col));
                                }
                                return row;
                        }
                });
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonObject json = new JsonObject();
                json.add("header", gson.toJsonTree(header, new TypeToken<List<String>>(){}.getType()));
                json.add("rows", gson.toJsonTree(rows, new TypeToken<List<List<String>>>(){}.getType()));
                //return gson.toJson(json);
                return gson.toJson(rows, new TypeToken<List<List<String>>>(){}.getType());
        }

}
