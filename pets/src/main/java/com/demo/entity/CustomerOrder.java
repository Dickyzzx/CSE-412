package com.demo.entity;

import java.math.BigDecimal;

public class CustomerOrder {

    private String uid;
    private String pid;
    private Integer identifier;
    private BigDecimal price;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "uid='" + uid + '\'' +
                ", pid='" + pid + '\'' +
                ", identifier=" + identifier +
                ", price=" + price +
                '}';
    }

    public CustomerOrder() {
    }
}
