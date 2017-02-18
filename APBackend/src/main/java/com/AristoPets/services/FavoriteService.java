package com.AristoPets.services;


import com.AristoPets.entity.Favorite;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface FavoriteService {

    HttpStatus addFavoriteAnimal(Favorite favorite);

    HttpStatus addFavoriteAdvert(Favorite favorite);

    Favorite deleteFavorite(long favoriteId, long userId) throws IllegalAccessException;

    List<Favorite> getAllFavoriteAnimalsByUserId(long userId);

    List<Favorite> getAllFavoriteAdvertsByUserId(long userId);
}
