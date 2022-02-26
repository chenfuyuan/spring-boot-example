package com.learn.project.springboot.example.security.system.web.request;

import com.learn.project.springboot.example.security.system.entity.User;
import com.learn.project.springboot.example.security.system.validator.FullName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 用户注册请求
 *
 * @author chenfuyuan
 * @date 2022/2/26 22:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @FullName
    @NotBlank
    private String fullName;

    /**
     * 转换成User对象
     * @return
     */
    public User toUser() {
        return User.builder().fullName(this.getFullName())
                .userName(this.getUserName())
                .enabled(true).build();
    }
}
