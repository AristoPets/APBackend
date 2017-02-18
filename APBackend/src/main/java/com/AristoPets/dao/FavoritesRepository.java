package com.AristoPets.dao;

import com.AristoPets.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FavoritesRepository extends JpaRepository<Favorite, Long> {
    Favorite findByUserIdAndAnimalId(long userId, long animalId);

    Favorite findByUserIdAndAdvertId(long userId, long advertId);

    @Query("SELECT f FROM Favorite f JOIN FETCH f.advert a WHERE f.userId = (:userId) AND f.animalId IS null AND a.archived = 0")
    List<Favorite> findFavoriteAdvertsByUserId(@Param("userId") long userId);

    @Query("SELECT f FROM Favorite f JOIN FETCH f.animal a WHERE f.userId = (:userId) AND f.advertId IS null AND a.archived = 0")
    List<Favorite> findFavoriteAnimalsByUserId(@Param("userId") long userId);
}
