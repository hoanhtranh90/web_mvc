package com.cnpm.webadmin.repository;

import com.cnpm.webadmin.entity.FkKeyPosProduct;
import com.cnpm.webadmin.entity.Pos;
import com.cnpm.webadmin.entity.PosProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PosProductRepository extends JpaRepository<PosProduct, FkKeyPosProduct> {

}
