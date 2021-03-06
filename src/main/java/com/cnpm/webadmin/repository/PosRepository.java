package com.cnpm.webadmin.repository;

import com.cnpm.webadmin.entity.Pos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PosRepository extends JpaRepository<Pos, Long> {
    @Query("select distinct p from Pos p left join ViewPosProduct v on p.id = v.posId where (:keyword is null or (LOWER(v.productName)) like :keyword )")
    Page<Pos> searchAll(String keyword, Pageable pageable);
}
