package com.reto;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * ServletInitializer to run spring boot app.
 *
 * @author patedwins
 * @version 1.0.0
 */
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * configure
     *
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppWebApplication.class);
    }

}
