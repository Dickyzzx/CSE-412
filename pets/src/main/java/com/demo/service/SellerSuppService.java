package com.demo.service;

import com.demo.entity.SellerSupp;

import java.util.List;

public interface SellerSuppService {

    List<SellerSupp> selectAll();

    List<SellerSupp> selectSellerSuppByUid();

}
