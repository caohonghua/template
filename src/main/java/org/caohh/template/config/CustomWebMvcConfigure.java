package org.caohh.template.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

@Configuration
public class CustomWebMvcConfigure implements WebMvcConfigurer {

    @Autowired
    private CustomConfig customConfig;

    @Autowired
    private HttpServletResponse response;

    /**
     * 跨域配置
     *
     * @param registry 跨域注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(customConfig.getCors().getMapping())
                .allowedOrigins(customConfig.getCors().getOrigins())
                .allowCredentials(customConfig.getCors().getCredentials())
                .allowedMethods(customConfig.getCors().getMethods())
                .maxAge(customConfig.getCors().getMaxAge());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                String token = request.getHeader(customConfig.getAuthKey());
                if (StringUtils.isEmpty(token)) {
                    ResponseEntity<String> entity = new ResponseEntity<>("令牌无效", HttpStatus.UNAUTHORIZED);
                    try (Writer writer = response.getWriter()) {
                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        writer.write(new ObjectMapper().writeValueAsString(entity));
                    }
                    return false;
                }
                // 过期，令牌无效

                return false;
            }
        });
    }
}
