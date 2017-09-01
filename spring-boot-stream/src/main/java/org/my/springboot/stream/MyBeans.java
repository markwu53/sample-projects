package org.my.springboot.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeans {

    @Autowired Application app;

    @Bean
    public String myAnotherBean() {
        String message = app.getClass().getName();
        //return "My another bean";
        return message;
    }
 
    @Bean
    public Integer myIntegerBean() {
        return 5;
    }

    @Bean
    public Integer myIntegerBean2() {
        return 6;
    }

}
