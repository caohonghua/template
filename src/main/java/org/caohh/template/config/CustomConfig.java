package org.caohh.template.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "custom")
public class CustomConfig {
    private String authKey;
    private CORSConfig cors;

    @Getter
    @Setter
    public static class CORSConfig {
        private String mapping = "/**";
        private String origins = "*";
        private Boolean credentials = Boolean.TRUE;
        private String[] methods = new String[]{"GET", "POST", "DELETE", "PUT"};
        private Long maxAge = 3600L;
    }
}
