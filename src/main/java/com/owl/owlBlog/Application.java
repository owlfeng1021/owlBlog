package com.owl.owlBlog;


import com.owl.owlBlog.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching

public class Application {

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
