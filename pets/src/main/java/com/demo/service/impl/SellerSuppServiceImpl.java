package com.demo.service.impl;

import com.demo.dao.Impl.SellerSuppDaoImpl;
import com.demo.dao.SellerSuppDao;
import com.demo.entity.SellerSupp;
import com.demo.entity.dto.UserMap;
import com.demo.service.SellerSuppService;
import com.demo.util.AuthUtil;

import java.util.List;

public class SellerSuppServiceImpl implements SellerSuppService {

    private SellerSuppDao sellerSuppDao = new SellerSuppDaoImpl();

    @Override
    public List<SellerSupp> selectAll() {
        if (AuthUtil.isLogin()) {
            return null;
        }
        return sellerSuppDao.selectAll();
    }

    @Override
    public List<SellerSupp> selectSellerSuppByUid() {
        if (AuthUtil.isLogin()) {
            return null;
        }
        return sellerSuppDao.selectByUid(UserMap.getUid());
    }
}
