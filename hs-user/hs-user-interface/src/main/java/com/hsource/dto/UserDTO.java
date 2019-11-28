package com.hsource.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不为空")
    @Length(min = 4,max = 12, message = "长度必须为 6-12")
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 注册手机号
     */
    private String phone;
}
