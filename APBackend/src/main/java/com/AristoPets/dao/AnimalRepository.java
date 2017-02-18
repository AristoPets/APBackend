package com.AristoPets.dao;

import com.AristoPets.entity.Animal;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AnimalRepository extends JpaRepository<Animal, Long>, QueryDslPredicateExecutor<Animal> {

    @Query("SELECT DISTINCT a FROM Animal a WHERE a.userId = (:id) AND a.archived = 0")
    List<Animal> findByUserID(@Param("id") long userId);

    @Query("SELECT a FROM Animal a WHERE a.archived = 0")
    List<Animal> findAllAnimals();

    @Query("UPDATE Animal a SET a.archived=1 WHERE a.id = ?1")
    @Modifying
    int deleteAnimal(long animalId);

    @Query("SELECT a FROM Animal a WHERE a.archived = 0 AND a.id = (:animalId)")
    Animal findAnimal(@Param("animalId") long id);

    @Query("SELECT a FROM Animal a WHERE a.archived = 0 ORDER BY a.id DESC")
    Page<Animal> findAllAnimals(Pageable pageable);

    @Override
    Page<Animal> findAll(Predicate predicate, Pageable pageable);
}