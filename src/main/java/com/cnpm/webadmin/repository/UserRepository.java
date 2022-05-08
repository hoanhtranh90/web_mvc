/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.cnpm.webadmin.repository;

import com.cnpm.webadmin.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author DELL
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            value = "SELECT u FROM User u WHERE LOWER(u.userName) =:userName  and u.isDelete=:isDelete")
    User findByUserName(String userName, Long isDelete);

    @Override
    Optional<User> findById(Long userId);

    User findByUserIdAndIsDelete(Long userId, Long isDelete);


    @Query(
            value = "SELECT u FROM User u WHERE LOWER(u.email) =:email  and u.isDelete=:isDelete")
    User findByEmail(String email, Long isDelete);
}
