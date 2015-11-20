package com.markwu.spring.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MysqlController {

        @Autowired @Qualifier("mysqlJdbc") private JdbcTemplate jdbc;
        @Autowired private Utils utils;

        @RequestMapping(value = "/mysql/schema", method = RequestMethod.GET)
        public String schemas(Model model) {
                List<String> schemas = jdbc.queryForList("show schemas", String.class);
                model.addAttribute("schemas", schemas);
                return "/jsp/schemas.jsp";
        }

        @RequestMapping(value = "/mysql/schema/{schema}", method = RequestMethod.GET)
        public String tables(@PathVariable String schema, Model model) {
                jdbc.execute("use " + schema);
                List<String> tables = jdbc.queryForList("show tables", String.class);
                model.addAttribute("schema", schema).addAttribute("tables", tables);
                return "/jsp/tables.jsp";
        }

        @RequestMapping(value = "/mysql/schema/{schema}/table/{table}", method = RequestMethod.GET)
        public String tableData(@PathVariable("schema") String schema, @PathVariable("table") String table, Model model) {
                String sql = String.format("select * from %s.%s", schema, table);
                List<String> header = new ArrayList<String>();
                List<List<String>> rows = utils.myQuery(jdbc, sql, header);
                model
                .addAttribute("schema", schema).addAttribute("table", table)
                .addAttribute("tableHeader", header).addAttribute("tableRows", rows);
                return "/jsp/tabledata.jsp";
        }

}
