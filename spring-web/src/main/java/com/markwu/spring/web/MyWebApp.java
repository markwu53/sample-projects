package com.markwu.spring.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
@EnableWebMvc
@Configuration
//@ComponentScan("com.markwu.spring.web")
//@ComponentScan(basePackages = {"com.markwu.spring.web"})
public class MyWebApp extends WebMvcConfigurerAdapter implements WebApplicationInitializer {

        private static AnnotationConfigWebApplicationContext appContext;

        @Override
        public void onStartup(ServletContext container) {
                appContext = new AnnotationConfigWebApplicationContext();
                appContext.register(MyWebApp.class);

                container.addListener(new ContextLoaderListener(appContext));

                Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(appContext));
                dispatcher.setLoadOnStartup(1);
                dispatcher.addMapping("/");
        }

        @RequestMapping(value = "/hello", method = RequestMethod.GET)
        @ResponseBody
        public String hello() {
                System.out.println(String.format("appContext is %s", appContext == null? "null" : "not null"));
                return appContext.getBean("helloBean", HelloBean.class).getMessage();
                //return "<h1>Hello World from spring web again ok</h1>";
        }

        @Bean
        public HelloBean helloBean() {
                return new HelloBean();
        }

}
