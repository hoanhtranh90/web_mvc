package com.cnpm.webadmin.model;

import com.cnpm.webadmin.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class CreatePosDTO {
    private List<ProductForCreatePosDTO> products;
    private Long totalPrice;
}
