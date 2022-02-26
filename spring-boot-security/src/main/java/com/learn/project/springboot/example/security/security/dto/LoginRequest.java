package com.learn.project.springboot.example.security.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 登录请求
 *
 * @author chenfuyuan
 * @date 2022/2/26 23:07
 */
@Data
@AllArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
    private Boolean rememberMe;
}

