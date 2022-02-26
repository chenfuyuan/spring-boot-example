package com.learn.project.springboot.example.security.system.service.impl;

import com.google.common.collect.ImmutableMap;
import com.learn.project.springboot.example.security.system.entity.Role;
import com.learn.project.springboot.example.security.system.entity.User;
import com.learn.project.springboot.example.security.system.entity.UserRole;
import com.learn.project.springboot.example.security.system.enums.RoleType;
import com.learn.project.springboot.example.security.system.excpetion.RoleNotFoundException;
import com.learn.project.springboot.example.security.system.excpetion.UserNameAlreadyExistException;
import com.learn.project.springboot.example.security.system.excpetion.UserNameNotFoundException;
import com.learn.project.springboot.example.security.system.repository.RoleRepository;
import com.learn.project.springboot.example.security.system.repository.UserRepository;
import com.learn.project.springboot.example.security.system.repository.UserRoleRepository;
import com.learn.project.springboot.example.security.system.service.UserService;
import com.learn.project.springboot.example.security.system.web.UserRepresentation;
import com.learn.project.springboot.example.security.system.web.request.UserRegisterRequest;
import com.learn.project.springboot.example.security.system.web.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 用户服务实现类
 *
 * @author chenfuyuan
 * @date 2022/2/26 22:43
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    public static final String USERNAME = "username:";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(UserRegisterRequest userRegisterRequest) {
        ensureUserNameNotExist(userRegisterRequest.getUserName());
        User user = userRegisterRequest.toUser();
        user.setPassword(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()));
        userRepository.save(user);
        //给用户绑定两个角色：用户和管理者
        Role studentRole = roleRepository.findByName(RoleType.USER.getName()).orElseThrow(() -> new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.USER.getName())));
        Role managerRole = roleRepository.findByName(RoleType.MANAGER.getName()).orElseThrow(() -> new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.MANAGER.getName())));
        userRoleRepository.save(new UserRole(user, studentRole));
        userRoleRepository.save(new UserRole(user, managerRole));
    }

    @Override
    public User find(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new UserNameNotFoundException(ImmutableMap.of(USERNAME, userName)));
    }

    @Override
    public void update(UserUpdateRequest userUpdateRequest) {
        User user = find(userUpdateRequest.getUserName());
        if (Objects.nonNull(userUpdateRequest.getFullName())) {
            user.setFullName(userUpdateRequest.getFullName());
        }
        if (Objects.nonNull(userUpdateRequest.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(userUpdateRequest.getPassword()));
        }
        if (Objects.nonNull(userUpdateRequest.getEnabled())) {
            user.setEnabled(userUpdateRequest.getEnabled());
        }
        userRepository.save(user);
    }


    @Override
    public void delete(String userName) {
        if (!userRepository.existsByUserName(userName)) {
            throw new UserNameNotFoundException(ImmutableMap.of(USERNAME, userName));
        }
        userRepository.deleteByUserName(userName);
    }

    @Override
    public Page<UserRepresentation> getAll(int pageNum, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum, pageSize)).map(User::toUserRepresentation);
    }

    @Override
    public boolean check(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword, password);
    }

    /**
     * 确认用户是否存在-根据用户名
     * @param userName 用户名
     */
    private void ensureUserNameNotExist(String userName) {
        boolean exist = userRepository.findByUserName(userName).isPresent();
        if (exist) {
            throw new UserNameAlreadyExistException(ImmutableMap.of(USERNAME, userName));
        }
    }
}
