package com.learn.project.springboot.example.security.security.service;

import com.learn.project.springboot.example.security.security.dto.LoginRequest;

/**
 * 权限服务
 *
 * @author chenfuyuan
 * @date 2022/2/26 23:08
 */
public interface AuthService {

    /**
     * 创建Token
     * @param loginRequest 登录请求
     * @return Token
     */
    String createToken(LoginRequest loginRequest);

    /**
     * 移除Token
     */
    void removeToken();
}
