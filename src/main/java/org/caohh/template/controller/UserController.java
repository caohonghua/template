package org.caohh.template.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.caohh.template.service.UserService;
import org.caohh.template.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登陆接口
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping(value = "/user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("登陆")
    @PostMapping(value = "/login")
    public String login(@RequestBody User user) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(userService.login(user));
    }
}
