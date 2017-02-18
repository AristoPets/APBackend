package com.AristoPets.services;


import com.AristoPets.dto.AdvertDto;
import com.AristoPets.entity.Advert;
import com.AristoPets.exceptions.WrongFieldInputException;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdvertService {

    List<Advert> findAll();

    Advert findOne(long id);

    List<Advert> findByUserId(long userId);

    Advert create(AdvertDto advertDto);

    Advert update(AdvertDto advertDto) throws WrongFieldInputException;

    Advert delete(long advertId, long userId) throws IllegalAccessException;

    Page<Advert> findAdvertsForCatalog(Pageable pageable);

    Page<Advert> findAdvertsBySearchParams(Predicate predicate, Pageable pageable);

    List<Advert> findByParentId(long parentId);

    //TODO: переделать
    Page<Advert> findAdvertsEmptySearch(Pageable pageable);
}
