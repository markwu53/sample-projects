package com.markwu.spring.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

        @RequestMapping("/user")
        public String user() {
                return "user";
        }

}
