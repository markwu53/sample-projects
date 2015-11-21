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
public class FiopsController {

        @Autowired @Qualifier("fiopsDataSource") private DataSource dataSource;
        @Autowired private Utils utils;

        public FiopsController() {
                System.out.println("init fiops controller");
        }

        @RequestMapping(value = "/oracle/fiops/schema", method = RequestMethod.GET)
        public String fiopsSchemas(Model model) {
                JdbcTemplate jdbc = new JdbcTemplate(dataSource);
                List<String> schemas = jdbc.queryForList("select username from dba_users order by username", String.class);
                model.addAttribute("schemas", schemas);
                return "/jsp/schemas.jsp";
        }

        @RequestMapping(value = "/oracle/fiops/schema/{schema}", method = RequestMethod.GET)
        public String fiopsTables(@PathVariable String schema, Model model) {
                JdbcTemplate jdbc = new JdbcTemplate(dataSource);
                List<String> tables = jdbc.queryForList("select table_name from dba_tables where owner=? order by table_name", new String[]{ schema }, String.class);
                model.addAttribute("schema", schema).addAttribute("tables", tables);
                return "/jsp/tables.jsp";
        }

        @RequestMapping(value = "/oracle/fiops/schema/{schema}/table/{table}", method = RequestMethod.GET)
        public String fiopsTableData(@PathVariable("schema") String schema, @PathVariable("table") String table, Model model) {
                JdbcTemplate jdbc = new JdbcTemplate(dataSource);
                String sql = String.format("select * from %s.%s where rownum <= 500", schema, table);
                List<String> header = new ArrayList<String>();
                List<List<String>> rows = utils.myQuery(jdbc, sql, header);
                model
                .addAttribute("schema", schema).addAttribute("table", table)
                .addAttribute("tableHeader", header).addAttribute("tableRows", rows);
                return "/jsp/tabledata.jsp";
        }

}
