package com.learn.project.springboot.example.security.system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色
 *
 * @author chenfuyuan
 * @date 2022/2/26 22:04
 */
@Data
@NoArgsConstructor
@Entity
@Table(name="role")
public class Role extends AbstractAuditBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
