package com.demo.service.impl;

import com.demo.dao.CustomerOrderDao;
import com.demo.dao.Impl.CustomerOrderDaoImpl;
import com.demo.entity.CustomerOrder;
import com.demo.entity.dto.UserMap;
import com.demo.service.CustomerOrderService;
import com.demo.util.AuthUtil;

import java.util.List;

public class CustomerOrderServiceImpl implements CustomerOrderService {

    private CustomerOrderDao orderDao = new CustomerOrderDaoImpl();

    @Override
    public List<CustomerOrder> selectAll() {
        if (AuthUtil.isLogin()) {
            return null;
        }
        return orderDao.selectAll();
    }

    @Override
    public List<CustomerOrder> selectOrderByUid() {
        if (AuthUtil.isLogin()) {
            return null;
        }
        return orderDao.selectByUid(UserMap.getUid());
    }
}
