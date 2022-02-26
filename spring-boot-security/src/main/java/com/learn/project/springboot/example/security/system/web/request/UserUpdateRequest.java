package com.learn.project.springboot.example.security.system.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 用户更新
 *
 * @author chenfuyuan
 * @date 2022/2/26 22:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    @NotBlank
    private String userName;
    private String password;
    private String fullName;
    private Boolean enabled;
}
