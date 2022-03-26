package com.demo.service;

import com.demo.entity.CustomerOrder;

import java.util.List;

public interface CustomerOrderService {

    List<CustomerOrder> selectAll();

    List<CustomerOrder> selectOrderByUid();

}
