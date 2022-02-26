package com.learn.project.springboot.example.security.system.repository;

import com.learn.project.springboot.example.security.system.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户-角色 关联信息
 *
 * @author chenfuyuan
 * @date 2022/2/26 22:12
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
}
