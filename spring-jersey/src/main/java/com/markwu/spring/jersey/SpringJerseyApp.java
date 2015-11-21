package com.markwu.spring.jersey;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class SpringJerseyApp implements WebApplicationInitializer {

        @Override
        public void onStartup(ServletContext servletContext) throws ServletException {
                AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
                appContext.register(SpringJerseyApp.class);
                servletContext.addListener(new ContextLoaderListener(appContext));
                Dynamic dispatcher = servletContext.addServlet("dispatcher", new ServletContainer(new ResourceConfig().packages("com.markwu.spring.jersey")));
                dispatcher.setLoadOnStartup(1);
                dispatcher.addMapping("/*");
        }

}
