package com.demo.service;

import com.demo.entity.Product;

import java.util.List;

public interface ProductService extends IService<Product> {

    List<Product> selectAll(String desc);

    List<Product> selectProductsByUid();

    Boolean deleteProduct(String id);

    Boolean buyProduct(String id);
}
