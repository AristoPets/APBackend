package com.AristoPets.dao;

import com.AristoPets.entity.Breeds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BreedsRepository extends JpaRepository<Breeds, Long> {
    @Override
    List<Breeds> findAll();
}
