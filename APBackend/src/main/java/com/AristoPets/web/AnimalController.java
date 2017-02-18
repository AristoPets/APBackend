package com.AristoPets.web;

import com.AristoPets.entity.Advert;
import com.AristoPets.entity.Animal;
import com.AristoPets.entity.User;
import com.AristoPets.exceptions.AnimalNotFoundException;
import com.AristoPets.services.AdvertService;
import com.AristoPets.services.AnimalService;
import com.AristoPets.services.UserService;
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
public class AnimalController {

    private final AnimalService animalService;
    private final AdvertService advertService;
    private final UserService userService;
    private final HeaderMapper headerMapper;

    @Autowired
    public AnimalController(AnimalService animalService, AdvertService advertService, UserService userService, HeaderMapper headerMapper) {
        this.animalService = animalService;
        this.advertService = advertService;
        this.userService = userService;
        this.headerMapper = headerMapper;
    }

    @GetMapping("/animal/{id}")
    public String getAnimalByAnimalId(HttpServletRequest request, @PathVariable("id") long id, Model model) throws AnimalNotFoundException {
        headerMapper.fillHeaderHtmlFragment(request, model);

        Animal animal = animalService.findAnimal(id);
        if(animal == null){
            throw new AnimalNotFoundException("No such Animal was found");
        }

        User userBreeder = userService.getUser(animal.getUserId());
        long userId = userBreeder.getId();

        List<Advert> advertsBreeds = advertService.findByUserId(userId);
        List<Animal> animals = animalService.findByUserId(userId);
        Set<String> breedsOfUser = animals.stream().map(animalB -> animalB.getBreed().getName()).collect(Collectors.toSet());
        breedsOfUser.addAll(advertsBreeds.stream().map(advertB -> advertB.getBreed().getName()).collect(Collectors.toSet()));

        List<Advert> adverts = advertService.findByParentId(id);
        model.addAttribute("userBreeder", userBreeder);
        model.addAttribute("animal", animal);
        model.addAttribute("adverts", adverts);
        model.addAttribute("breedsOfUser", breedsOfUser);
        return "animal";
    }
}
