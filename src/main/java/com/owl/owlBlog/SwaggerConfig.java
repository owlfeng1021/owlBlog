package com.owl.owlBlog;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger.enable:true}")
public class SwaggerConfig {
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot APIs",
                "Spring Boot + Swagger2",
                "1.0.0",
                null,
                "owl的博客",
                "作者：owlfeng",
                "http://www.owlfeng.cn/");
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.owl.owlBlog.controller"))
//                .apis(RequestHandlerSelectors.basePackage("com.owl.owlBlog.controller.admin"))
//                .paths(regex("/admin/.*"))
//               开放所有路径
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo)
                .useDefaultResponseMessages(false);
        return docket;
    }
}