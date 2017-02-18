package com.AristoPets.web;

import com.AristoPets.entity.Advert;
import com.AristoPets.entity.Animal;
import com.AristoPets.services.AdvertService;
import com.AristoPets.services.AnimalService;
import com.AristoPets.webconfig.URLS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/list")
public class ListController {

    private final AdvertService advertService;
    private final AnimalService animalService;
    private final HeaderMapper headerMapper;

    @Autowired
    public ListController(AdvertService advertService, AnimalService animalService, HeaderMapper headerMapper) {
        this.advertService = advertService;
        this.animalService = animalService;
        this.headerMapper = headerMapper;
    }


    @GetMapping("/adverts")
    public String getAdvertsPage(@PageableDefault(page = 0, size = 15) Pageable pageable,
                                 HttpServletRequest request, Model model) {
        headerMapper.fillHeaderHtmlFragment(request, model);

        model.addAttribute("order", "DESC");
        model.addAttribute("property", "id");

        Page<Advert> page = advertService.findAdvertsForCatalog(pageable);
        int totalPages = page.getTotalPages();
        if (pageable.getPageNumber() > totalPages) {
            return "redirect:" + URLS.ADVERTS_LIST + "?page=0";
        }
        Pagination.paginate(page,model, request.getQueryString());

        List<Advert> adverts = page.getContent();
        model.addAttribute("favUrl", URLS.API.ABSOLUTE_FAVORITE_ADVERT);
        model.addAttribute("adverts", adverts);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);
        return "advertsList";
    }

    @GetMapping("/animals")
    public String getAnimalsPage(@PageableDefault(page = 0, size = 15) Pageable pageable,
                                 HttpServletRequest request, Model model) {
        headerMapper.fillHeaderHtmlFragment(request, model);

        Page<Animal> page = animalService.findAnimalsForCatalog(pageable);
        int totalPages = page.getTotalPages();
        if (pageable.getPageNumber() > totalPages) {
            return "redirect:" + URLS.ANIMALS_LIST;
        }
        Pagination.paginate(page,model, request.getQueryString());

        List<Animal> animals = page.getContent();
        model.addAttribute("favUrl", URLS.API.ABSOLUTE_FAVORITE_ANIMAL);
        model.addAttribute("animals", animals);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);
        return "animalsList";
    }
}
