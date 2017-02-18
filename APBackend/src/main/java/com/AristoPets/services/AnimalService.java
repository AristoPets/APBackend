package com.AristoPets.services;

import com.AristoPets.dto.AnimalDto;
import com.AristoPets.entity.Animal;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnimalService {

    List<Animal> findByUserId(long id);

    Animal saveAndFlush(Animal animal);

    Animal saveAndFlush(AnimalDto animalDto);

    Animal findAnimal(long id);

    Animal update(AnimalDto animal);

    Animal delete(long animalId, long userId) throws IllegalAccessException;

    Page<Animal> findAnimalsForCatalog(Pageable pageable);

    Page<Animal> findAnimalsBySearchParams(Predicate predicate, Pageable pageable);
}
