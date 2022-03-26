package com.demo.service;

import com.demo.entity.Pets;

import java.util.List;

public interface PetsService extends IService<Pets> {

    List<Pets> selectAll(String name);

    List<Pets> selectPetsByUid();

    Boolean deletePets(String id);

    Boolean buyPets(String id);
}
