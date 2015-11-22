package com.markwu.spring.jersey;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PhoenixController {

        @Autowired @Qualifier("phoenixDataSource") private DataSource dataSource;
        @Autowired private Utils utils;

        @RequestMapping(value = "/phoenix/table", method = RequestMethod.GET)
        public String tables(Model model) {
                JdbcTemplate jdbc = new JdbcTemplate(dataSource);
                String sql = String.format("select distinct table_schem, table_name from system.catalog");
                List<String> header = new ArrayList<String>();
                List<List<String>> rows = utils.myQuery(jdbc, sql, header);
                model.addAttribute("tables", rows);
                return "/jsp/phoenix_tables.jsp";
        }

        @RequestMapping(value = "/phoenix/schema/{schema}/table/{table}", method = RequestMethod.GET)
        public String tableData(@PathVariable("schema") String schema, @PathVariable("table") String table, Model model) {
                JdbcTemplate jdbc = new JdbcTemplate(dataSource);
                String sql = String.format("select * from %s", schema.equals("null")? table : schema + "." + table);
                List<String> header = new ArrayList<String>();
                List<List<String>> rows = null;
                try {
                        rows = utils.myQuery(jdbc, sql, header);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                model.addAttribute("table", table).addAttribute("tableHeader", header).addAttribute("tableRows", rows);
                return "/jsp/tabledata.jsp";
        }

}
