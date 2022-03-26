package com.demo.service.impl;

import com.demo.dao.Impl.PetsDaoIml;
import com.demo.dao.PetsDao;
import com.demo.entity.Pets;
import com.demo.entity.dto.UserMap;
import com.demo.service.PetsService;
import com.demo.util.AuthUtil;

import java.util.List;

public class PetsServiceImpl implements PetsService {

    private PetsDao petsDao = new PetsDaoIml();

    public Boolean saveOne(Pets entity) {
        if (AuthUtil.isLogin()) {
            return false;
        }
        entity.setSellerUid(UserMap.getUid());
        return petsDao.insertOne(entity);
    }

    public List<Pets> selectAll(String name) {
        if (AuthUtil.isLogin()) {
            return null;
        }
        return petsDao.selectAll(name);
    }

    public List<Pets> selectPetsByUid() {
        if (AuthUtil.isLogin()) {
            return null;
        }
        return petsDao.selectPetsByUid(UserMap.getUid());
    }

    public Boolean deletePets(String id) {
        if (AuthUtil.isLogin()) {
            return false;
        }
        return petsDao.deleteById(id, UserMap.getUid());
    }

    @Override
    public Boolean buyPets(String id) {
        if (AuthUtil.isLogin()) {
            return false;
        }
        return petsDao.buyPets(id, UserMap.getUid());
    }
}
