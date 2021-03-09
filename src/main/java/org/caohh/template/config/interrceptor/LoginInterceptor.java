package org.caohh.template.config.interrceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.caohh.template.config.CustomConfig;
import org.caohh.template.tool.ApplicationContextTool;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * 登陆拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getDispatcherType() == DispatcherType.ERROR) {
            return true;
        }
        if (request.getMethod().equals(HttpMethod.HEAD.name())) {
            return false;
        }
        CustomConfig config = ApplicationContextTool.getBean(CustomConfig.class);
        String token = request.getHeader(config.getAuthKey());
        if (StringUtils.isEmpty(token)) {
            ResponseEntity<String> entity = new ResponseEntity<>("未传递令牌", HttpStatus.BAD_REQUEST);
            String msg = new ObjectMapper().writeValueAsString(entity);
            writeInfo(response, msg);
            return false;
        }
        // 过期，令牌无效
        StringRedisTemplate redisTemplate = ApplicationContextTool.getBean(StringRedisTemplate.class);
        Boolean result = redisTemplate.hasKey(token);
        if (Objects.isNull(result) || Boolean.FALSE.equals(result)) {
            ResponseEntity<String> entity = new ResponseEntity<>("令牌无效", HttpStatus.UNAUTHORIZED);
            String msg = new ObjectMapper().writeValueAsString(entity);
            writeInfo(response, msg);
            return false;
        }
        return true;
    }

    private void writeInfo(HttpServletResponse response, String msg) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try (PrintWriter writer = response.getWriter()) {
            writer.write(msg);
        }
    }
}
