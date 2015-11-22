package com.markwu.spring.jersey;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringJerseyApp implements WebApplicationInitializer {

        public static WebApplicationContext appContext;

        @Override
        public void onStartup(ServletContext servletContext) throws ServletException {
                AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
                context.register(SpringConfig.class);
                appContext = context;

                servletContext.addListener(new ContextLoaderListener(appContext));

                Servlet springServlet = new DispatcherServlet(appContext);
                Dynamic springDispatcher = servletContext.addServlet("springDispatcher", springServlet);
                springDispatcher.setLoadOnStartup(1);
                springDispatcher.addMapping("/spring/*");

                Servlet jerseyServlet = new ServletContainer(new ResourceConfig().packages("com.markwu.spring.jersey"));
                Dynamic jerseyDispatcher = servletContext.addServlet("jerseyDispatcher", jerseyServlet);
                jerseyDispatcher.setLoadOnStartup(1);
                jerseyDispatcher.addMapping("/jersey/*");
        }

}
