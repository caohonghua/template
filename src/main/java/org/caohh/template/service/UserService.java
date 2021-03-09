package org.caohh.template.service;

import org.caohh.template.vo.User;
import org.springframework.http.HttpEntity;

/**
 * 用户服务接口
 */
public interface UserService {
    /**
     * 登陆接口
     *
     * @param user 用户信息
     * @return 登陆结果
     */
    HttpEntity<Object> login(User user);
}
