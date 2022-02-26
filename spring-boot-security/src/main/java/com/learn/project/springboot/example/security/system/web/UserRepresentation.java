package com.learn.project.springboot.example.security.system.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/2/26 22:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRepresentation {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 全名
     */
    private String fullName;
}
