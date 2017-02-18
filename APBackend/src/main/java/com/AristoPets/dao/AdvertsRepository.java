package com.AristoPets.dao;


import com.AristoPets.entity.Advert;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertsRepository extends JpaRepository<Advert, Long>, QueryDslPredicateExecutor<Advert> {

    @Override
    Page<Advert> findAll(Predicate predicate, Pageable pageable);

    @Query("SELECT adv FROM Advert adv WHERE adv.archived = 0")
    List<Advert> findAllAdverts();

    @Query(value = "SELECT DISTINCT adv FROM Advert adv LEFT JOIN FETCH adv.advertItems WHERE adv.userId = (:id) AND adv.archived = 0")
    List<Advert> findByUserID(@Param("id") long userId);

    @Query(value = "SELECT adv FROM Advert adv WHERE adv.archived = 0 ORDER BY adv.id DESC")
    Page<Advert> findAllAdverts(Pageable pageable);

    //TODO: переделать
    @Query(value = "SELECT adv FROM Advert adv WHERE adv.archived = 0")
    Page<Advert> findAdvertsEmptySearch(Pageable pageable);

    @Query("SELECT adv FROM Advert adv WHERE adv.archived = 0 AND (adv.mParentId = (:parentId) OR adv.fParentId = (:parentId))")
    List<Advert> findByParentId(@Param("parentId") long id);

    @Query("SELECT a FROM Advert a WHERE a.archived = 0 AND a.id = (:advertId)")
    Advert findAdvert(@Param("advertId") long id);

    @Query("UPDATE Advert a SET a.archived=1 WHERE a.id = ?1")
    @Modifying
    int deleteAdvert(long advertId);
}
