package com.demo.dao;

import com.demo.entity.Pets;

import java.util.List;

public interface PetsDao extends BaseDao<Pets> {

    List<Pets> selectAll(String name);

    List<Pets> selectPetsByUid(String uid);

    Boolean deleteById(String id, String uid);

    Boolean buyPets(String id, String uid);
}
