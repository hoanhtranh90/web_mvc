/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.cnpm.webadmin.repository;

import com.cnpm.webadmin.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DELL
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query(
            value = "SELECT  new UserRole(ur.userRoleId) FROM UserRole ur WHERE ur.user.userId=:userId")
    List<UserRole> findByUserUserIdForAssignPermission(Long userId);



    @Query(
            value = "SELECT r.roleCode FROM UserRole ur JOIN ur.user u JOIN ur.role r WHERE u.userId=:userId")
    String findRoleCodeByUserUserId(Long userId);


}
