package com.cnpm.webadmin.authentication;

import com.cnpm.webadmin.entity.Role;
import com.cnpm.webadmin.entity.User;

import com.cnpm.webadmin.exception.PermissionException;
import com.cnpm.webadmin.until.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AuthUntil {

    public static User getCurrentUser() {
        try {
            return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal()).getUser();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

    }
    public static boolean checkLogin() {
        return getPrincipal() != null;
    }



    public static Role getCurrentRole() {
        try {
            CustomUserDetails customUserDetails = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal());
            List<Role> roles = new ArrayList<>(customUserDetails.getRoleOfUser());
            return StringUtils.isTrue(roles) ? roles.get(0) : null;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

    }

    public static CustomUserDetails getPrincipal() {
        try {
            return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal());
        } catch (Exception e) {
            log.error("Here get null: " + e.getMessage(), e);
            return null;
        }

    }

    public static List<String> getRoleCurrentUser() {
        List<String> roles = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            roles.add(auth.getAuthority());
        }
        return roles;
    }
    public static void checkHasRole(final String roleCode) throws PermissionException {
        if (getRoleCurrentUser().isEmpty() || (null != roleCode
                && !roleCode.equalsIgnoreCase(getRoleCurrentUser().get(0)))) {
            throw new PermissionException("Xin lỗi bạn không có quyền.");
        }
    }
    public static void checkHasRole1(final List<String> roleCode) throws PermissionException {
       /* if (ApplicationUtils.getRoleCurrentUser().isEmpty() || (null != roleCode)) {
            for (  String role : roleCode) {
                if(ApplicationUtils.getRoleCurrentUser().contains(role)){
                    break;
                }
            }

            throw new PermissionException("Xin lỗi bạn không có quyền.");
        }*/
        if(!checkRole(roleCode)){
            throw new PermissionException("Xin lỗi bạn không có quyền.");
        }
    }

    public static boolean checkRole(final List<String> roleCode) {
        if (getRoleCurrentUser().isEmpty() || (null != roleCode)) {
            for (  String role : roleCode) {
                if(getRoleCurrentUser().contains(role)){
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
