package com.demo.entity;

import java.math.BigDecimal;

public class Product {

    private String id;
    private String description;
    private String brand;
    private BigDecimal price;
    private String status;
    private String sellerUid;
    private String buyUid;

    public String getBuyUid() {
        return buyUid;
    }

    public void setBuyUid(String buyUid) {
        this.buyUid = buyUid;
    }

    public String getSellerUid() {
        return sellerUid;
    }

    public void setSellerUid(String sellerUid) {
        this.sellerUid = sellerUid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", sellerUid='" + sellerUid + '\'' +
                ", buyUid='" + buyUid + '\'' +
                '}';
    }

    public Product() {
    }
}
