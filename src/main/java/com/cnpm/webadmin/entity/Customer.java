package com.cnpm.webadmin.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "customer")
public class Customer extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 2679118992937555761L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone", unique = true)
    private String phone;
}
