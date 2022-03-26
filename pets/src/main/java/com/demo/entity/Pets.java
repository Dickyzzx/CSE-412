package com.demo.entity;

import java.math.BigDecimal;

public class Pets {

    private String id;
    private String name;
    private Integer age;
    private String location;
    private String species;
    private String breed;
    private BigDecimal price;
    private String sellerUid;
    private String status;
    private String buyUid;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuyUid() {
        return buyUid;
    }

    public void setBuyUid(String buyUid) {
        this.buyUid = buyUid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSellerUid() {
        return sellerUid;
    }

    public void setSellerUid(String sellerUid) {
        this.sellerUid = sellerUid;
    }

    @Override
    public String toString() {
        return "Pets{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", location='" + location + '\'' +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", price=" + price +
                ", sellerUid='" + sellerUid + '\'' +
                ", status='" + status + '\'' +
                ", buyUid='" + buyUid + '\'' +
                '}';
    }

    public Pets() {
    }
}
