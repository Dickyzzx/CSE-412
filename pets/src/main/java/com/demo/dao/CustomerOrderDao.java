package com.demo.dao;

import com.demo.entity.CustomerOrder;

import java.util.List;

public interface CustomerOrderDao {

    List<CustomerOrder> selectByUid(String uid);

    List<CustomerOrder> selectAll();
}
