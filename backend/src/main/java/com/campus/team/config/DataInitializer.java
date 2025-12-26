package com.campus.team.config;

import com.campus.team.entity.User;
import com.campus.team.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) {
        // 检查是否已存在管理员账户
        if (!userRepository.existsByEmail("admin@campus.edu.cn")) {
            User admin = new User();
            admin.setEmail("admin@campus.edu.cn");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setNickname("系统管理员");
            admin.setAvatar("https://api.dicebear.com/7.x/avataaars/svg?seed=admin");
            admin.setRole("ADMIN");
            admin.setStatus("NORMAL");
            admin.setAllowInvite(false);
            admin.setCollege("系统");
            admin.setMajor("管理");
            admin.setGrade("管理员");
            
            userRepository.save(admin);
            log.info("===========================================");
            log.info("【初始化】管理员账户创建成功");
            log.info("【初始化】邮箱: admin@campus.edu.cn");
            log.info("【初始化】密码: admin123");
            log.info("===========================================");
        }
    }
}
