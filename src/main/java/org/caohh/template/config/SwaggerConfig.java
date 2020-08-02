package org.caohh.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestAPI() {
        Contact contact = new Contact("caohh", "", "1010590379@qq.com");
        ApiInfo info = new ApiInfoBuilder()
                .title("Template")
                .description("A Spring-Boot, Mybatis, Mybatis-Dynamic, Swagger Template Project")
                .version("0.1")
                .license("The Apache License")
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.caohh.template.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(info);
    }
}
