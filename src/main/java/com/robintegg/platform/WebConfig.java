package com.robintegg.platform;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addResourceHandler("favicon.ico", "/content/**")
                .addResourceLocations("/public", "classpath:/static/");
                //.resourceChain(true)
                //.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
    }
}