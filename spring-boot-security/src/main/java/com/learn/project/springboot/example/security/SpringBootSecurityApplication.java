package com.learn.project.springboot.example.security;

import com.learn.project.springboot.example.security.system.entity.Role;
import com.learn.project.springboot.example.security.system.entity.User;
import com.learn.project.springboot.example.security.system.entity.UserRole;
import com.learn.project.springboot.example.security.system.enums.RoleType;
import com.learn.project.springboot.example.security.system.repository.RoleRepository;
import com.learn.project.springboot.example.security.system.repository.UserRepository;
import com.learn.project.springboot.example.security.system.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootSecurityApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;



    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityApplication.class, args);
    }

    /**
     * 初始化h2数据库
     * @param args
     */
    @Override
    public void run(String... args) {
        //初始化角色信息
        for (RoleType roleType : RoleType.values()) {
            roleRepository.save(new Role(roleType.getName(), roleType.getDescription()));
        }
        //初始化一个 admin 用户
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = User.builder().enabled(true).fullName("admin").userName("root").password(bCryptPasswordEncoder.encode("root")).build();
        userRepository.save(user);
        Role role = roleRepository.findByName(RoleType.ADMIN.getName()).get();
        userRoleRepository.save(new UserRole(user, role));
    }
}
