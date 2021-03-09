package org.caohh.template.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户登陆信息
 */
@ApiModel(value = "用户", description = "用户信息")
@Getter
@Setter
@ToString
public class User implements Serializable {
    // 用户名
    @ApiModelProperty(name = "username", value = "用户名")
    private String username;
    // 密码
    @ApiModelProperty(name = "password", value = "密码")
    private String password;
}
