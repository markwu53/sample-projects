package com.markwu.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

        @Autowired private HelloBean helloBean;

        @RequestMapping(value = "/hello", method = RequestMethod.GET)
        @ResponseBody
        public String hello() {
                return helloBean.getMessage();
        }

        @RequestMapping(value = "/welcome", method = RequestMethod.GET)
        public String welcome() {
                return "welcome";
        }

}
