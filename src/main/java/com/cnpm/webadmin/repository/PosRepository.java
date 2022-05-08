package com.cnpm.webadmin.repository;

import com.cnpm.webadmin.entity.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosRepository extends JpaRepository<Pos, Long> {
}
