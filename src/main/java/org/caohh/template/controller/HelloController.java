package org.caohh.template.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Hello")
@RestController
@RequestMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HelloController {

    @ApiOperation("sayHello")
    @GetMapping("/{name}")
    public String sayHello(@ApiParam(name = "name", value = "名称", required = true) @PathVariable String name) throws JsonProcessingException {
        String result = String.format("Hello, %s", name);
        ResponseEntity<String> entity = new ResponseEntity<>(result, HttpStatus.OK);
        return new ObjectMapper().writeValueAsString(entity);
    }
}
