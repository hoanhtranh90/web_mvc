package com.cnpm.webadmin.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@Table(name = "product")
public class Product extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 2679118992937555761L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //product code - khong duoc trung nhau
    @Column(name = "product_code", unique = true)
    private String productCode;

    //product name
    @Column(name = "product_name")
    private String productName;

    //product price
    @Column(name = "product_price")
    private Long productPrice;


    @JsonManagedReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @OrderBy("pos_id ASC")
    private List<PosProduct> posProductList;

}
