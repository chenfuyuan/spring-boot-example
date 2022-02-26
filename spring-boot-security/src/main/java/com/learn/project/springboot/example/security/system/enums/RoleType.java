package com.learn.project.springboot.example.security.system.enums;

import lombok.Getter;

/**
 * 角色类型
 *
 * @author chenfuyuan
 * @date 2022/2/26 22:01
 */
@Getter
public enum RoleType {
    USER("USER", "用户"),
    TEMP_USER("TEMP_USER", "临时用户"),
    MANAGER("MANAGER", "管理者"),
    ADMIN("ADMIN", "Admin");
    private final String name;
    private final String description;

    RoleType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
