package com.AristoPets.api;


import com.AristoPets.dto.AdvertDto;
import com.AristoPets.entity.Advert;
import com.AristoPets.entity.User;
import com.AristoPets.entity.enums.UserType;
import com.AristoPets.exceptions.WrongFieldInputException;
import com.AristoPets.services.AdvertService;
import com.AristoPets.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/advert")
public class ApiAdvertController {

    private AdvertService advertService;
    private UserService userService;

    @Autowired
    public ApiAdvertController(AdvertService advertService, UserService userService) {
        this.advertService = advertService;
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getAdvertById(@PathVariable("id") long advertId) {
        Advert advert = advertService.findOne(advertId);

        if (advert == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(advert);
    }

    @GetMapping("/")
    public ResponseEntity<?> getUserAdverts(HttpServletRequest request) {
        User user = (User) request.getAttribute("userData");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<Advert> adverts = advertService.findByUserId(user.getId());
        if (adverts == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(adverts);
    }

    @PostMapping("/")
    public ResponseEntity<?> createAdvert(@RequestBody AdvertDto advertDto,
                                          HttpServletRequest request) {
        User user = (User) request.getAttribute("userData");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        advertDto.setUserId(user.getId());
        Advert createdAdvert = advertService.create(advertDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/advert/{id}").buildAndExpand(createdAdvert.getId()).toUri();
        return ResponseEntity.created(location).body(createdAdvert);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdvert(@RequestBody AdvertDto advertDto, @PathVariable("id") long advertId,
                                          HttpServletRequest request) {
        User user = (User) request.getAttribute("userData");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        long userId = user.getId();
        Advert existingAdvert = advertService.findOne(advertId);
        if (existingAdvert == null || existingAdvert.isArchived()) {
            return ResponseEntity.noContent().build();
        }
        if (existingAdvert.getUserId() != userId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        advertDto.setId(advertId);
        advertDto.setUserId(userId);
        try {
            existingAdvert = advertService.update(advertDto);
        } catch (WrongFieldInputException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(existingAdvert);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteAnimal(HttpServletRequest request, @PathVariable(name = "id") long advertId) {
        User user = (User) request.getAttribute("userData");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Advert advert;
        try {
            advert = advertService.delete(advertId, user.getId());
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if (advert == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(advert);
    }

    private boolean isUserBreeder(long userId) {
        return userService.getUser(userId).getUserType() == UserType.BREEDER;
    }

}
