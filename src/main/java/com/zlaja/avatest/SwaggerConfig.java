package com.zlaja.avatest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zlaja.avatest"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(newArrayList(basicAuth()))
                .apiInfo(metaData());
    }

    private BasicAuth basicAuth() {
        return new BasicAuth("Authorization");
    }

    private ApiInfo metaData() {
        return new ApiInfo(
                "Spring Boot Rule Based Access Control",
                "Spring Boot Rule Based Access Control Secured REST API for Online Store",
                "1.0",
                "Terms of service",
                new Contact("Zlatko Salbut", "", ""),
                "",
                "");
    }

}
