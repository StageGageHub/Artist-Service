package com.stagegage.services.servlet;

import com.stagegage.services.Main;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by Scott on 7/19/14.
 *
 * @author Scott Hendrickson
 */
public class ArtistServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Main.class);
    }
}
