package com.owl.owlBlog.config;

import com.owl.owlBlog.interceptor.OnlineNumberListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MywebConfig implements WebMvcConfigurer {
    @Bean
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new OnlineNumberListener());
        System.out.println("listener");
        return srb;
    }
}
