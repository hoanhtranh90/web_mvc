package com.cnpm.webadmin.service;

import com.cnpm.webadmin.entity.FkKeyPosProduct;
import com.cnpm.webadmin.entity.Pos;
import com.cnpm.webadmin.entity.PosProduct;
import com.cnpm.webadmin.entity.Product;
import com.cnpm.webadmin.model.CreatePosDTO;
import com.cnpm.webadmin.model.ProductForCreatePosDTO;
import com.cnpm.webadmin.repository.PosProductRepository;
import com.cnpm.webadmin.repository.PosRepository;
import com.cnpm.webadmin.until.ApplicationUtils;
import com.cnpm.webadmin.until.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PosService {
    @Autowired
    private PosRepository posRepository;

    @Autowired
    private PosProductRepository posProductRepository;

    @Transactional
    public void save(CreatePosDTO pos) {

        //save pos
        Pos pos1 = new Pos();
        pos1.setTotalPrice(pos.getTotalPrice());
        posRepository.save(pos1);

        //save pos product
        for (ProductForCreatePosDTO product : pos.getProducts()) {
            PosProduct posProduct = new PosProduct();
            posProduct.setPos(pos1);
            posProduct.setQuantity(product.getQuantity());
            posProduct.setProduct(convertProductDTOToProduct(product));
            FkKeyPosProduct fkKeyPosProduct = new FkKeyPosProduct( pos1.getId(), product.getId());
            posProduct.setId(fkKeyPosProduct);
            posProductRepository.save(posProduct);
        }
    }

    private Product convertProductDTOToProduct(ProductForCreatePosDTO product) {
        Product product1 = new Product();
        product1.setId(product.getId());
        product1.setProductName(product.getProductName());
        product1.setProductCode(product.getProductCode());
        product1.setProductPrice(product.getProductPrice());
        return product1;
    }

    public Page<Pos> findAll(int pageNumber, int size, String sortByProperties, String sortBy, String keyword) {

        Sort sort = ApplicationUtils.getSort(sortByProperties, sortBy);
        Pageable pageable = PageRequest.of(pageNumber -1, size, sort);
        keyword = StringUtils.buildLikeExp(keyword);
        List<Pos> userBasicDtos = new ArrayList<>();
        Page<Pos> page = posRepository.searchAll(keyword, pageable);
        page.getContent().stream().forEach(u -> {
            userBasicDtos.add((u));
        });
        return new PageImpl<>(userBasicDtos, pageable, page.getTotalElements());

    }
}
