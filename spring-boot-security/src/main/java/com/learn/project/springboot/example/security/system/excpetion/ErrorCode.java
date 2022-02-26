package com.learn.project.springboot.example.security.system.excpetion;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 错误码
 *
 * @author chenfuyuan
 * @date 2022/2/26 22:51
 */
@Getter
public enum ErrorCode {
    USER_NAME_ALREADY_EXIST(1001, HttpStatus.BAD_REQUEST, "用户名已经存在"),
    Role_NOT_FOUND(1002, HttpStatus.NOT_FOUND, "未找到指定角色"),
    USER_NAME_NOT_FOUND(1002, HttpStatus.NOT_FOUND, "未找到指定用户"),
    VERIFY_JWT_FAILED(1003, HttpStatus.UNAUTHORIZED, "token验证失败"),
    METHOD_ARGUMENT_NOT_VALID(1003, HttpStatus.BAD_REQUEST, "方法参数验证失败"),
    SYSTEM_ERROR(500,HttpStatus.INTERNAL_SERVER_ERROR,"系统错误");
    private final int code;

    private final HttpStatus status;

    private final String message;

    ErrorCode(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
