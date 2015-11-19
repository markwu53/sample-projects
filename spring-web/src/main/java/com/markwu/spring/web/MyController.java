package com.markwu.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class MyController {

        @Autowired private HelloBean helloBean;
        @Autowired private WebApplicationContext appContext;

        @RequestMapping(value = "/hello", method = RequestMethod.GET)
        @ResponseBody
        public String hello() {
                return helloBean.getMessage();
        }

        @RequestMapping(value = "/welcome", method = RequestMethod.GET)
        public String welcome(Model model, Model model2) {
                //System.out.println(Arrays.asList(appContext.getBeanDefinitionNames()).toString());
                StringBuilder html = new StringBuilder("<p>");
                for (String name: appContext.getBeanDefinitionNames()) {
                        html.append(name);
                        html.append("<br/>");
                }
                html.append("</p>");
                model.addAttribute("beans", html.toString());
                model2.addAttribute("greeting", "Hello");
                return "welcome";
        }

}
