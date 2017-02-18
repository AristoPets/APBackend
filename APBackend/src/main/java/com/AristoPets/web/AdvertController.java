package com.AristoPets.web;

import com.AristoPets.entity.*;
import com.AristoPets.exceptions.AdvertNotFoundException;
import com.AristoPets.services.AdvertService;
import com.AristoPets.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AdvertController {

    private final AdvertService advertService;
    private final HeaderMapper headerMapper;
    private final AnimalService animalService;

    @Autowired
    public AdvertController(AdvertService advertService, HeaderMapper headerMapper, AnimalService animalService) {
        this.advertService = advertService;
        this.headerMapper = headerMapper;
        this.animalService = animalService;
    }


    @GetMapping("/advert/{id}")
    public String getAdvert(HttpServletRequest request, @PathVariable("id") long id, Model model) throws AdvertNotFoundException {
        headerMapper.fillHeaderHtmlFragment(request, model);

        Advert advert = advertService.findOne(id);
        if(advert == null){
            throw new AdvertNotFoundException("No such Advert");
        }

        List<AdvertItem> advertItems = advert.getAdvertItems();
        User userBreeder = advert.getUser();
        long userId = userBreeder.getId();

        List<Advert> adverts = advertService.findByUserId(userId);
        List<Animal> animals = animalService.findByUserId(userId);
        Set<String> breedsOfUser = animals.stream().map(animal -> animal.getBreed().getName()).collect(Collectors.toSet());
        breedsOfUser.addAll(adverts.stream().map(advertB -> advertB.getBreed().getName()).collect(Collectors.toSet()));

        Animal animalFather = animalService.findAnimal(advert.getmParentId());
        Animal animalMother = animalService.findAnimal(advert.getfParentId());

        model.addAttribute("advert", advert);
        model.addAttribute("advertItems", advertItems);
        model.addAttribute("userBreeder", userBreeder);
        model.addAttribute("father", animalFather);
        model.addAttribute("mother", animalMother);
        model.addAttribute("breedsOfUser", breedsOfUser);

        return "advert";
    }

}
