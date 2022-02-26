package com.learn.project.springboot.example.security.security.service.impl;

import com.learn.project.springboot.example.security.security.entity.JwtUser;
import com.learn.project.springboot.example.security.security.common.utils.CurrentUserUtils;
import com.learn.project.springboot.example.security.security.common.utils.JwtTokenUtils;
import com.learn.project.springboot.example.security.security.dto.LoginRequest;
import com.learn.project.springboot.example.security.security.service.AuthService;
import com.learn.project.springboot.example.security.system.entity.User;
import com.learn.project.springboot.example.security.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限服务实现类
 *
 * @author chenfuyuan
 * @date 2022/2/26 23:08
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final StringRedisTemplate stringRedisTemplate;
    private final CurrentUserUtils currentUserUtils;

    @Override
    public String createToken(LoginRequest loginRequest) {
        //查找用户
        User user = userService.find(loginRequest.getUsername());
        //校验用户名和密码是否匹配
        if (!userService.check(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("The user name or password is not correct.");
        }

        //构造jwtUser
        JwtUser jwtUser = new JwtUser(user);
        if (!jwtUser.isEnabled()) {
            throw new BadCredentialsException("User is forbidden to login");
        }
        List<String> authorities = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = JwtTokenUtils.createToken(user.getUserName(), user.getId().toString(), authorities, loginRequest.getRememberMe());
        stringRedisTemplate.opsForValue().set(user.getId().toString(), token);
        return token;
    }

    @Override
    public void removeToken() {
        stringRedisTemplate.delete(currentUserUtils.getCurrentUser().getId().toString());
    }
}
