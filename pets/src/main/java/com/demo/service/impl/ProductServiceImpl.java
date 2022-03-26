package com.demo.service.impl;

import com.demo.dao.Impl.ProductDaoImpl;
import com.demo.dao.ProductDao;
import com.demo.entity.Product;
import com.demo.entity.dto.UserMap;
import com.demo.service.ProductService;
import com.demo.util.AuthUtil;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<Product> selectAll(String desc) {
        if (AuthUtil.isLogin()) {
            return null;
        }
        return productDao.selectAll(desc);
    }

    @Override
    public List<Product> selectProductsByUid() {
        if (AuthUtil.isLogin()) {
            return null;
        }
        return productDao.selectProductsByUid(UserMap.getUid());
    }

    @Override
    public Boolean deleteProduct(String id) {
        if (AuthUtil.isLogin()) {
            return null;
        }
        return productDao.deleteById(id, UserMap.getUid());
    }

    @Override
    public Boolean buyProduct(String id) {
        if (AuthUtil.isLogin()) {
            return false;
        }
        return productDao.buyProduct(id, UserMap.getUid());
    }

    @Override
    public Boolean saveOne(Product entity) {
        if (AuthUtil.isLogin()) {
            return null;
        }
        entity.setSellerUid(UserMap.getUid());
        return productDao.insertOne(entity);
    }
}
