package com.cnpm.webadmin.model;

import com.cnpm.webadmin.entity.Pos;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.List;

@Data
public class ProductForCreatePosDTO {
    private Long id;
    private String productCode;
    private String productName;
    private Long productPrice;
    private Long quantity;
}
