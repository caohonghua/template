package org.caohh.template.service.impl;

import org.caohh.template.service.UserService;
import org.caohh.template.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用户服务实现层
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public HttpEntity<Object> login(User user) {
        if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
            String token = UUID.nameUUIDFromBytes((user.getUsername() + ":" + user.getPassword()).getBytes()).toString();
            if (!Boolean.TRUE.equals(redisTemplate.hasKey(token))) {
                redisTemplate.opsForValue().set(token, user.toString());
            }
            redisTemplate.expire(token, 30, TimeUnit.MINUTES);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("账号或密码不正确", HttpStatus.FORBIDDEN);
    }
}
