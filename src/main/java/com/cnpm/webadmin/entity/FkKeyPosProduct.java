package com.cnpm.webadmin.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FkKeyPosProduct implements Serializable {

    @Column(name = "pos_id")
    long posId;

    @Column(name = "product_id")
    long productId;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) posId;
        hash += (int)  productId;
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FkKeyPosProduct)) {
            return false;
        }
        FkKeyPosProduct other = (FkKeyPosProduct) object;
        if (this.posId != other.posId) {
            return false;
        }
        if (this.productId != other.productId) {
            return false;
        }
        return true;
    }

    public FkKeyPosProduct() {
    }

    public FkKeyPosProduct(Long posId, Long productId) {
        this.posId = posId;
        this.productId = productId;
    }
}
