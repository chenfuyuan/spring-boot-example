package com.learn.project.springboot.example.security.system.repository;

import com.learn.project.springboot.example.security.system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 角色仓储
 *
 * @author chenfuyuan
 * @date 2022/2/26 22:03
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String roleName);
}
