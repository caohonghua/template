package org.caohh.template.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 */
@Configuration(proxyBeanMethods = false)
public class CORSConfig {

    @Autowired
    private CustomConfig config;

    @Bean
    public WebMvcConfigurer corsConfigure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(config.getCors().getMapping())
                        .allowedOrigins(config.getCors().getOrigins())
                        .allowCredentials(true)
                        .allowedHeaders(config.getCors().getHeaders())
                        .allowedMethods(config.getCors().getMethods())
                        .maxAge(config.getCors().getMaxAge());
            }
        };
    }
}
