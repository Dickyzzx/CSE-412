package com.demo.dao;

import com.demo.entity.SellerSupp;

import java.util.List;

public interface SellerSuppDao {

    List<SellerSupp> selectByUid(String uid);

    List<SellerSupp> selectAll();

}
