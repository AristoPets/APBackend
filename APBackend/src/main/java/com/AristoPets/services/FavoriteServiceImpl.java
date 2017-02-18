package com.AristoPets.services;

import com.AristoPets.dao.AdvertsRepository;
import com.AristoPets.dao.AnimalRepository;
import com.AristoPets.dao.FavoritesRepository;
import com.AristoPets.entity.Advert;
import com.AristoPets.entity.Animal;
import com.AristoPets.entity.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoritesRepository favoritesRepository;
    private final AnimalRepository animalRepository;
    private final AdvertsRepository advertsRepository;

    @Autowired
    public FavoriteServiceImpl(FavoritesRepository favoritesRepository, AnimalRepository animalRepository, AdvertsRepository advertsRepository) {
        this.favoritesRepository = favoritesRepository;
        this.animalRepository = animalRepository;
        this.advertsRepository = advertsRepository;
    }


    @Override
    public HttpStatus addFavoriteAnimal(Favorite newFavorite) {
        long animalId = newFavorite.getAnimalId();
        Favorite favorite = favoritesRepository.findByUserIdAndAnimalId(newFavorite.getUserId(), animalId);
        Animal favoriteAnimal = animalRepository.findAnimal(animalId);
        if (favoriteAnimal == null) {
            return HttpStatus.NOT_FOUND;
        }
        if (favorite == null) {
            newFavorite.setAnimal(favoriteAnimal);
            favoritesRepository.save(newFavorite);
            return HttpStatus.CREATED;
        }
        return HttpStatus.CONFLICT;
    }

    @Override
    public HttpStatus addFavoriteAdvert(Favorite newFavorite) {
        long advertId = newFavorite.getAdvertId();
        Favorite favorite = favoritesRepository.findByUserIdAndAdvertId(newFavorite.getUserId(), advertId);
        Advert favoriteAdvert = advertsRepository.findAdvert(advertId);
        if (favoriteAdvert == null) {
            return HttpStatus.NOT_FOUND;
        }
        if (favorite == null) {
            newFavorite.setAdvert(favoriteAdvert);
            favoritesRepository.save(newFavorite);
            return HttpStatus.CREATED;
        }
        return HttpStatus.CONFLICT;
    }

    @Override
    public Favorite deleteFavorite(long favoriteId, long userId) throws IllegalAccessException {
        Favorite favorite = favoritesRepository.findOne(favoriteId);
        if (favorite == null) {
            return null;
        }
        if (favorite.getUserId() != userId) {
            throw new IllegalAccessException("Access denied.User has no permissions");
        }
        favoritesRepository.delete(favoriteId);
        return favorite;
    }

    @Override
    public List<Favorite> getAllFavoriteAnimalsByUserId(long userId) {
        List<Favorite> favoriteAnimals = favoritesRepository.findFavoriteAnimalsByUserId(userId);
        return favoriteAnimals;
    }

    @Override
    public List<Favorite> getAllFavoriteAdvertsByUserId(long userId) {
        List<Favorite> favoriteAdverts = favoritesRepository.findFavoriteAdvertsByUserId(userId);
        return favoriteAdverts;
    }
}
