package com.cnpm.webadmin.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class PosProduct extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 2679118992937555761L;

    @EmbeddedId
    private FkKeyPosProduct id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("posId")
    @JoinColumn(name = "pos_id")
    private Pos pos;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    //quantity
    @Column(name = "quantity")
    private Long quantity;

}
