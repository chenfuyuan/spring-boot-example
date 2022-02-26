package com.learn.project.springboot.example.security.system.service;

import com.learn.project.springboot.example.security.system.entity.User;
import com.learn.project.springboot.example.security.system.web.UserRepresentation;
import com.learn.project.springboot.example.security.system.web.request.UserRegisterRequest;
import com.learn.project.springboot.example.security.system.web.request.UserUpdateRequest;
import org.springframework.data.domain.Page;

/**
 * 用户服务接口
 *
 * @author chenfuyuan
 * @date 2022/2/26 22:43
 */
public interface UserService {
    /**
     * 注册
     * @param userRegisterRequest 用户注册请求
     */
    void save(UserRegisterRequest userRegisterRequest);

    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @return
     */
    User find(String userName);

    /**
     * 更新用户
     * @param userUpdateRequest 用户更新请求
     */
    void update(UserUpdateRequest userUpdateRequest);

    /**
     * 删除用户-根据用户名
     * @param userName 用户名
     */
    void delete(String userName);

    /**
     * 分页查询用户
     * @param pageNum 页数
     * @param pageSize 一页的数据量
     * @return 查询结果
     */
    Page<UserRepresentation> getAll(int pageNum, int pageSize);

    /**
     * 校验
     * @param currentPassword 当前密码
     * @param password 密码
     * @return
     */
    boolean check(String currentPassword, String password);

}
