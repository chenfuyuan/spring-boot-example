package com.learn.project.springboot.example.security.system.excpetion;

import java.util.Map;

/**
 * 用户已存在异常
 *
 * @author chenfuyuan
 * @date 2022/2/26 22:53
 */
public class UserNameAlreadyExistException extends BaseException {

    public UserNameAlreadyExistException(Map<String, Object> data) {
        super(ErrorCode.USER_NAME_ALREADY_EXIST, data);
    }
}