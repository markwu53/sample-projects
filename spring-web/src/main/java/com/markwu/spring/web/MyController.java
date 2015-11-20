package com.markwu.spring.web;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class MyController {

        @Autowired private WebApplicationContext appContext;

        @RequestMapping(value = "/hello", method = RequestMethod.GET)
        @ResponseBody
        public String hello() {
                return "hello";
        }

        @RequestMapping(value = "/welcome", method = RequestMethod.GET)
        public String welcome(Model model, Model model2) {
                StringBuilder html = new StringBuilder("<p>");
                for (String name: appContext.getBeanDefinitionNames()) {
                        html.append(name);
                        html.append("<br/>");
                }
                html.append("</p>");
                model.addAttribute("beans", html.toString());
                model2.addAttribute("greeting", "Hello");
                return "/jsp/welcome.jsp";
        }

        @RequestMapping(value = "/mysql/schema", method = RequestMethod.GET)
        public String schemas(Model model) {
                JdbcTemplate jdbc = new JdbcTemplate(appContext.getBean("mysqlDataSource", DataSource.class));
                List<String> schemas = jdbc.queryForList("show schemas", String.class);
                model.addAttribute("schemas", schemas);
                return "/jsp/schemas.jsp";
        }

        @RequestMapping(value = "/mysql/schema/{schema}", method = RequestMethod.GET)
        public String showTables(@PathVariable String schema, Model model) {
                JdbcTemplate jdbc = new JdbcTemplate(appContext.getBean("mysqlDataSource", DataSource.class));
                jdbc.execute("use " + schema);
                List<String> tables = jdbc.queryForList("show tables", String.class);
                model.addAttribute("tables", tables);
                return "/jsp/showtable.jsp";
        }
}
