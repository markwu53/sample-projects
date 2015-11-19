package com.markwu.spring.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan("com.markwu.spring.web")
// @ComponentScan(basePackages = {"com.markwu.spring.web"})
public class MyWebApp extends WebMvcConfigurerAdapter implements WebApplicationInitializer {

        @Override
        public void onStartup(ServletContext container) {
                AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
                appContext.register(MyWebApp.class);
                container.addListener(new ContextLoaderListener(appContext));
                Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(appContext));
                dispatcher.setLoadOnStartup(1);
                dispatcher.addMapping("/");
        }

        @Bean
        public InternalResourceViewResolver viewResolver() {
                InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
                viewResolver.setPrefix("/jsp/");
                viewResolver.setSuffix(".jsp");
                return viewResolver;
        }

        @Bean
        public HelloBean helloBean() {
                return new HelloBean();
        }

}
