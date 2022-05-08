package com.cnpm.webadmin.authentication;

import com.cnpm.webadmin.entity.Role;
import com.cnpm.webadmin.entity.User;
import com.cnpm.webadmin.entity.UserRole;
import com.cnpm.webadmin.exception.ResourceNotFoundException;
import com.cnpm.webadmin.repository.UserRepository;
import com.cnpm.webadmin.until.Constants;
import com.cnpm.webadmin.until.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

/**
 *
 * @author Nguyen_Toan
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public CustomUserDetails loadUserById(Long id) throws ResourceNotFoundException {
//        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
//                messageSourceVi.getMessageVi("USERNAME_NOT_FOUND_MSG") + id));
        User user = userRepository.findByUserIdAndIsDelete(id, Constants.DELETE.NORMAL);

        if (null == user) {
            throw new UsernameNotFoundException("User not found with id: ");
        }
        HashSet<Role> rolesOfUser = new HashSet<>();
        user.getUserRoleList().stream().forEach((UserRole udr) -> {
            rolesOfUser.add(udr.getRole());
        });
        return new CustomUserDetails(user, user.getFullName(), rolesOfUser);
    }

    @Transactional
    public CustomUserDetails loadCustomUserByUsername(String username, String roleCode) {
        User user = userRepository.findByUserName(username, Constants.DELETE.NORMAL);

        if (null == user) {
            throw new UsernameNotFoundException("User not found with username: " );
        }

        HashSet<Role> rolesOfUser = new HashSet<>();
        user.getUserRoleList().stream().forEach((UserRole udr) -> {
            if (udr.getRole().getRoleCode().equalsIgnoreCase(roleCode)) {
                rolesOfUser.add(udr.getRole());
            }
        });

        return new CustomUserDetails(user, user.getFullName(), rolesOfUser);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = new User();
        if(StringUtils.isEmail(username)){
             user = userRepository.findByEmail(username, Constants.DELETE.NORMAL);
        }
        else user = userRepository.findByUserName(username, Constants.DELETE.NORMAL);
        if (null == user) {
            throw new UsernameNotFoundException("User not found with username: ");
        }
        if (!StringUtils.isTrue(user.getPassword())) {
            user.setCheckPassword(passwordEncoder.encode(username));
        }

        HashSet<Role> rolesOfUser = new HashSet<>();
        user.getUserRoleList().stream().map(UserRole::getRole).forEach(rolesOfUser::add);


        return new CustomUserDetails(user, user.getFullName(), rolesOfUser);
    }

}
