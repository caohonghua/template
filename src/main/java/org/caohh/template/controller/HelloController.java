package org.caohh.template.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Hello")
@RestController
@RequestMapping("/hello")
public class HelloController {

    @ApiOperation("sayHello")
    @GetMapping("/{name}")
    public String sayHello(@ApiParam(name = "name", value = "名称", required = true) @PathVariable String name) {
        return String.format("Hello, %s", name);
    }
}
