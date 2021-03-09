package org.caohh.template.config;

import org.caohh.template.config.interrceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMVC配置
 */
@Configuration
public class CustomWebMvcConfigure implements WebMvcConfigurer {

    @Autowired
    private CustomConfig config;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/doc.html", "/swagger-resources/**", "/webjars/**", "/v3/api-docs", "/user/login");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(config.getCors().getMapping())
                .allowedHeaders(config.getCors().getHeaders())
                .allowedMethods(config.getCors().getMethods())
                .maxAge(config.getCors().getMaxAge())
                .allowedOrigins(config.getCors().getOrigins());
    }
}
