package com.example.servlet;

import com.example.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Deploying to an application server
 * <p>
 * The WAR file will be useless unless it includes a web.xml file or a servlet initializer to enable Spring MVC’s
 * DispatcherServlet.
 */
public class ApplicationServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }
}
