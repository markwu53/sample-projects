package com.markwu.spring.web;

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

        public PhoenixController() {
                System.out.println("init phoenix contorller");
        }

        @RequestMapping(value = "/phoenix/table", method = RequestMethod.GET)
        public String schemas(Model model) {
                JdbcTemplate jdbc = new JdbcTemplate(dataSource);
                //String sql = String.format("select table_schem, table_name from system.catalog");
                String sql = "!tables";
                List<String> header = new ArrayList<String>();
                List<List<String>> rows = utils.myQuery(jdbc, sql, header);
                System.out.println(header);
                for (List<String> row: rows) System.out.println(row);
                return "/jsp/tables.jsp";
        }

        @RequestMapping(value = "/phoenix/table/{table}", method = RequestMethod.GET)
        public String tableData(@PathVariable("schema") String schema, @PathVariable("table") String table, Model model) {
                JdbcTemplate jdbc = new JdbcTemplate(dataSource);
                String sql = String.format("select * from %s.%s", schema, table);
                List<String> header = new ArrayList<String>();
                List<List<String>> rows = utils.myQuery(jdbc, sql, header);
                model
                .addAttribute("schema", schema).addAttribute("table", table)
                .addAttribute("tableHeader", header).addAttribute("tableRows", rows);
                return "/jsp/tabledata.jsp";
        }

}
