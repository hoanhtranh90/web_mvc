package com.cnpm.webadmin.entity.View;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "v_pos_product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ViewPosProduct implements Serializable {
    private static final long serialVersionUID = 2679118992937555761L;
    @Id
    @Column(name = "pos_id")
    private Long posId;

    //product_name
    @Column(name = "product_name")
    private String productName;
}
