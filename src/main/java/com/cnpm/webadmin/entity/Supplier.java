package com.cnpm.webadmin.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name = "supplier")
public class Supplier extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 2679118992937555761L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
