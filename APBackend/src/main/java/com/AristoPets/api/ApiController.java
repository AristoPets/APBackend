package com.AristoPets.api;

import com.AristoPets.entity.Advert;
import com.AristoPets.entity.Breeds;
import com.AristoPets.services.AdvertService;
import com.AristoPets.services.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ApiController {

    private BreedService breedService;
    private AdvertService advertService;

    @Autowired
    public ApiController(BreedService breedService, AdvertService advertService) {
        this.breedService = breedService;
        this.advertService = advertService;
    }


    @GetMapping("/api/breeds")
    public List<Breeds> getAllBreeds() {
        return breedService.findAll();
    }

    @GetMapping("/api/breeds/{id}")
    public Breeds getBreedById(@PathVariable("id") int id) {
        return breedService.find(id);
    }


    @GetMapping("/api/adverts/all")
    public List<Advert> getAllAdverts() {
        return advertService.findAll();
    }

}
