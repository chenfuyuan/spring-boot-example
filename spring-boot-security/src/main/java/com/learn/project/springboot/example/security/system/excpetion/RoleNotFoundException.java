package com.learn.project.springboot.example.security.system.excpetion;

import java.util.Map;

/**
 * 角色未找到异常
 *
 * @author chenfuyuan
 * @date 2022/2/26 22:53
 */
public class RoleNotFoundException extends BaseException {
    public RoleNotFoundException(Map<String, Object> data) {
        super(ErrorCode.Role_NOT_FOUND, data);
    }
}
