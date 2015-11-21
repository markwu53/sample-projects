package com.markwu.spring.jersey;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class Utils {

        /**
         * This function stores column names in the parameter "header".
         */
        public List<List<String>> myQuery(JdbcTemplate jdbc, String sql, final List<String> header) {
                return jdbc.query(sql, new RowMapper<List<String>>() {
                        private ResultSetMetaData meta;
                        private int columnCount = 1;
                        @Override
                        public List<String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                                if (header.isEmpty()) {
                                        meta = rs.getMetaData();
                                        columnCount = meta.getColumnCount();
                                        for (int col = 1; col <= columnCount; col ++) {
                                                header.add(meta.getColumnName(col));
                                        }
                                }
                                List<String> row = new ArrayList<String>();
                                for (int col = 1; col <= columnCount; col ++) {
                                        row.add(rs.getString(col));
                                }
                                return row;
                        }
                });
        }

}
