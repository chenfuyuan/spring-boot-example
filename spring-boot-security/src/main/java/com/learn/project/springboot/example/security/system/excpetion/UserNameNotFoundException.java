package com.learn.project.springboot.example.security.system.excpetion;

import java.util.Map;

/**
 * 用户未找到异常
 *
 * @author chenfuyuan
 * @date 2022/2/26 22:54
 */
public class UserNameNotFoundException extends BaseException {
    public UserNameNotFoundException(Map<String, Object> data) {
        super(ErrorCode.USER_NAME_NOT_FOUND, data);
    }
}

