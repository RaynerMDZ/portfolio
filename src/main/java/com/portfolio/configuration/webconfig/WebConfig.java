package com.portfolio.configuration.webconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
//@EnableWebMvc
//public class WebConfig extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler(
//                "/webjars/**",
//                "/images/**",
//                "/v2/css/**",
//                "/v2/javascript/**",
//                "/img/**",
//                "/css/**",
//                "/javascript/**")
//                .addResourceLocations(
//                        "classpath:/META-INF/resources/webjars/",
//                        "classpath:/static/v2/css",
//                        "classpath:/static/v2/javascript",
//                        "classpath:/static/images/",
//                        "classpath:/static/img/",
//                        "classpath:/static/css/",
//                        "classpath:/static/javascript/");
//    }
//
//}
