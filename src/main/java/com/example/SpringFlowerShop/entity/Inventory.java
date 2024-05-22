package com.example.SpringFlowerShop.entity;

import jakarta.persistence.*;


import java.sql.Date;
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @Column(name = "product_id", nullable = false)
    private Long productId;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "shipment_date", nullable = false)
    private Date shipmentDate;
    @OneToOne()
    @MapsId
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
