package com.AristoPets.web;


import com.AristoPets.entity.Advert;
import com.AristoPets.entity.Animal;
import com.AristoPets.entity.Breeds;
import com.AristoPets.entity.User;
import com.AristoPets.services.AdvertService;
import com.AristoPets.services.AnimalService;
import com.AristoPets.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/breeder")
public class BreederController {

    private final HeaderMapper headerMapper;
    private final AnimalService animalService;
    private final AdvertService advertService;
    private final UserService userService;

    @Autowired
    public BreederController(HeaderMapper headerMapper, AnimalService animalService, AdvertService advertService, UserService userService) {
        this.headerMapper = headerMapper;
        this.animalService = animalService;
        this.advertService = advertService;
        this.userService = userService;
    }


    @GetMapping("/adverts/{userId}")
    public String getBreederAdvertsByBreederId(HttpServletRequest request, @PathVariable(value = "userId") long id, Model model) {
        headerMapper.fillHeaderHtmlFragment(request, model);
        fillBreederPage(model,id,true);
        return "breeder_adverts";
    }

    @GetMapping("/animals/{userId}")
    public String getBreederAnimalsByBreederId(HttpServletRequest request, @PathVariable(value = "userId") long id, Model model) {
        headerMapper.fillHeaderHtmlFragment(request, model);
        fillBreederPage(model,id,false);
        return "breeder_animals";
    }


    private void fillBreederPage(Model model, long userId, boolean isAdvertsPage){
        User userBreeder = userService.getUser(userId);
        List<Advert> adverts = advertService.findByUserId(userId);
        List<Animal> animals = animalService.findByUserId(userId);
        Set<String> breedsOfUser = animals.stream().map(animal -> animal.getBreed().getName()).collect(Collectors.toSet());
        breedsOfUser.addAll(adverts.stream().map(advert -> advert.getBreed().getName()).collect(Collectors.toSet()));
        model.addAttribute("breedsOfUser", breedsOfUser);
        model.addAttribute("userBreeder", userBreeder);
        model.addAttribute("advertsCount", adverts.size());
        model.addAttribute("animalsCount", animals.size());
        if(isAdvertsPage){
            model.addAttribute("adverts", adverts);
        }else{
            model.addAttribute("animals", animals);
        }
    }
}
