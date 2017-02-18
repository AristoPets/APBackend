package com.AristoPets.services;

import com.AristoPets.dao.BreedsRepository;
import com.AristoPets.entity.Breeds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service("BreedService")
public class BreedServiceImpl implements BreedService {

    private final BreedsRepository breedsRepository;

    @Autowired
    public BreedServiceImpl(BreedsRepository breedsRepository) {
        this.breedsRepository = breedsRepository;
    }

    @Override
    public Breeds find(long id) {
        Breeds foundBreeds;
        if (id >= 0) {
            foundBreeds = breedsRepository.findOne(id);
        } else {
            return null;
        }

        return foundBreeds;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Breeds> findAll() {
        return breedsRepository.findAll();
    }

    @Override
    public void update(Breeds breeds) {
        breedsRepository.save(breeds);
    }

    @Override
    public void updateAll(List<Breeds> breedsList) {
        breedsRepository.save(breedsList);
    }
}
