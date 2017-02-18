package com.AristoPets.api;


import com.AristoPets.entity.Favorite;
import com.AristoPets.entity.User;
import com.AristoPets.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/favorite")
public class ApiFavoriteController {

    private FavoriteService favoriteService;

    @Autowired
    public ApiFavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }


    @PostMapping("/animal/{id}")
    public ResponseEntity<HttpStatus> addFavoriteAnimal(@PathVariable("id") long animalId, HttpServletRequest request){
        User user = (User) request.getAttribute("userData");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Favorite favorite = new Favorite();
        favorite.setAnimalId(animalId);
        favorite.setUserId(user.getId());
        HttpStatus httpStatus = favoriteService.addFavoriteAnimal(favorite);
        return ResponseEntity.status(httpStatus).build();
    }

    @PostMapping("/advert/{id}")
    public ResponseEntity<HttpStatus> addFavoriteAdvert(@PathVariable("id") long advertId, HttpServletRequest request){
        User user = (User) request.getAttribute("userData");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Favorite favorite = new Favorite();
        favorite.setAdvertId(advertId);
        favorite.setUserId(user.getId());
        HttpStatus httpStatus = favoriteService.addFavoriteAdvert(favorite);
        return ResponseEntity.status(httpStatus).build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteFavorite(@PathVariable("id") long favoriteId, HttpServletRequest request){
        User user = (User) request.getAttribute("userData");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Favorite favorite;
        try {
            favorite = favoriteService.deleteFavorite(favoriteId, user.getId());
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if (favorite == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(favorite);
    }
}
