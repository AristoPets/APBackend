package com.AristoPets.web;


import com.AristoPets.entity.Favorite;
import com.AristoPets.entity.User;
import com.AristoPets.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/favorites")
public class FavoritesController {

    private FavoriteService favoriteService;
    private HeaderMapper headerMapper;

    @Autowired
    public FavoritesController(FavoriteService favoriteService, HeaderMapper headerMapper) {
        this.favoriteService = favoriteService;
        this.headerMapper = headerMapper;
    }

    @GetMapping("/animals")
    public String getFavoriteAnimals(HttpServletRequest request, Model model) {
        User user = headerMapper.fillHeaderHtmlFragment(request, model);
        if (user == null) {
            return "favoriteAnimals";
        }

        List<Favorite> favoriteAnimals = favoriteService.getAllFavoriteAnimalsByUserId(user.getId());
        model.addAttribute("favoriteAnimals", favoriteAnimals);

        return "favoriteAnimals";
    }

    @GetMapping("/adverts")
    public String getFavoriteAdverts(HttpServletRequest request, Model model) {
        User user = headerMapper.fillHeaderHtmlFragment(request, model);
        if (user == null) {
            return "favoriteAnimals";
        }

        List<Favorite> favoriteAdverts = favoriteService.getAllFavoriteAdvertsByUserId(user.getId());
        model.addAttribute("favoriteAdverts", favoriteAdverts);

        return "favoriteItems";
    }
}
