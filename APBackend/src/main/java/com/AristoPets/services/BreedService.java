package com.AristoPets.services;

import com.AristoPets.entity.Breeds;

import java.util.List;


public interface BreedService {
    Breeds find(long id);

    List<Breeds> findAll();

    void update(Breeds breeds);

    void updateAll(List<Breeds> breedsList);
}
