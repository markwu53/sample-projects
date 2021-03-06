package com.markwu.spring.boot;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

        public static void main(String[] args) {
                new SpringApplication(Application.class).run(args);
        }

        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
                return builder.sources(Application.class);
        }

        @Bean
        public EmbeddedServletContainerCustomizer servletContainerCustomizer() {
                return new EmbeddedServletContainerCustomizer() {
                        public void customize(ConfigurableEmbeddedServletContainer container) {
                                //container.setPort(8086);
                                container.setDocumentRoot(new File("WebContent"));
                        }
                };
        }

}
