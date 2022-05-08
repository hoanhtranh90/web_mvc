package com.cnpm.webadmin.service.impl;

import com.cnpm.webadmin.entity.Role;
import com.cnpm.webadmin.entity.User;
import com.cnpm.webadmin.entity.UserRole;
import com.cnpm.webadmin.repository.RoleRepository;
import com.cnpm.webadmin.repository.UserRepository;
import com.cnpm.webadmin.repository.UserRoleRepository;
import com.cnpm.webadmin.service.AccountManagerService;
import com.cnpm.webadmin.until.Constants;
import com.cnpm.webadmin.until.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Primary
@Slf4j
@Qualifier("AcountManagementService_Main")
@Transactional
public class AccountManagerServiceImpl implements AccountManagerService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRoleRepository userRoleRepository;
    String accountManager = "hoanhtranh80";
    String emailManager = "nguyenkhacsang100@gmail.com";

    @Bean
    public CommandLineRunner initialAccountAdminAndApplication() {
        System.out.println("Initial account admin and application");
        return (args) -> {
            if (!StringUtils.isTrue(userRepository.findByUserName(accountManager, Constants.DELETE.NORMAL))) {
                Role role = roleRepository.findByRoleCode("ADMIN");
                User user = User.builder().email(emailManager).fullName("Nguyễn Khắc Sang")
                        .phoneNumber("0913055405")
                        .isDelete(Constants.DELETE.NORMAL)
                        .password(passwordEncoder.encode("123456a@A"))
                        .status(1L)
                        .userName(accountManager)
                        .userId(Long.valueOf(1)).build();

                UserRole userRole = UserRole.builder().role(role).user(userRepository.save(user))
                        .userRoleId(Long.valueOf(1)).build();

                userRoleRepository.save(userRole);
                log.info("Created account management.");
            } else {
                log.info("Account management already exsit.");
            }

        };
    }
}
