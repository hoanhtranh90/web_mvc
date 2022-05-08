package com.cnpm.webadmin.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "pos")
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Pos extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 2679118992937555761L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @JsonManagedReference
    @OneToMany(mappedBy = "pos", fetch = FetchType.LAZY)
    @OrderBy("pos_id ASC")
    private List<PosProduct> posProductList;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    //total price of pos
    @Column(name = "total_price")
    private Long totalPrice;
}
