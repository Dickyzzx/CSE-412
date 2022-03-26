package com.demo.dao;

import com.demo.entity.Product;

import java.util.List;

public interface ProductDao extends BaseDao<Product> {

    List<Product> selectAll(String desc);

    List<Product> selectProductsByUid(String uid);

    Boolean deleteById(String id, String uid);

    Boolean buyProduct(String id, String uid);
}
