package com.AristoPets.search;

import com.AristoPets.entity.Advert;
import com.AristoPets.entity.Animal;
import com.AristoPets.entity.Breeds;
import com.AristoPets.entity.enums.Gender;
import com.AristoPets.services.AdvertService;
import com.AristoPets.services.AnimalService;
import com.AristoPets.services.BreedService;
import com.AristoPets.web.Pagination;
import com.AristoPets.webconfig.URLS;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/search")
public class SearchController {


    private final AdvertService advertService;
    private final AnimalService animalService;
    private final BreedService breedService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SearchController(AdvertService advertService, AnimalService animalService, BreedService breedService) {
        this.advertService = advertService;
        this.animalService = animalService;
        this.breedService = breedService;
    }


    @GetMapping("/adverts")
    public String getAdvertsPageBySearchCriteria(@RequestParam(value = "title", required = false) String title,
                                                 @RequestParam(value = "breedId", required = false) String breedId,
                                                 @RequestParam(value = "property", required = false, defaultValue = "id") String property,
                                                 @RequestParam(value = "order", required = false, defaultValue = "DESC") String order,
                                                 @RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                                 @RequestParam(value = "minPrice", required = false) String minPrice,
                                                 @RequestParam(value = "maxPrice", required = false) String maxPrice,
                                                 HttpServletRequest request,
                                                 Model model) {
        Pageable pageable = new PageRequest(pageNumber, 10, new Sort(Sort.Direction.fromString(order), property));

        model.addAttribute("order", order);
        model.addAttribute("property", property);

        String searchParams = "";
        if(title != null && !title.equals("")){
            searchParams = "title=" + title + "&";
            model.addAttribute("title",title);
        }
        if(breedId != null && !breedId.equals("")){
            searchParams = searchParams + "breedId=" + breedId + "&";
            Breeds breed = breedService.find(Integer.valueOf(breedId));
            if(breed != null){
                model.addAttribute("breedName", breed.getName());
            }
        }
        if(minPrice != null && !minPrice.equals("")){
            searchParams = searchParams +  "averagePrice>" + minPrice + "&";
            model.addAttribute("minPrice",minPrice);
        }
        if(maxPrice != null && !maxPrice.equals("")){
            searchParams = searchParams +  "averagePrice<" + maxPrice + "&";
            model.addAttribute("maxPrice",maxPrice);
        }

        String fullSearch = null;
        if(!searchParams.equals("")){
            fullSearch = searchParams;
        }

        if (fullSearch == null) {
            Page<Advert> page = advertService.findAdvertsEmptySearch(pageable);
            List<Advert> advertsEmptySearch = page.getContent();
            int totalPagesEmptySearch = page.getTotalPages();
            Pagination.paginate(page, model,request.getQueryString());
            model.addAttribute("adverts", advertsEmptySearch);
            model.addAttribute("page", page);
            model.addAttribute("totalPages", totalPagesEmptySearch);
            return "advertsList";
        }


        PredicateBuilder<Advert> builder = new PredicateBuilder<>();
        Pattern pattern = Pattern.compile("(\\w+?)(=|<|>)([\\p{L}\\s.,!0-9]+)&");
        Matcher matcher = pattern.matcher(fullSearch + "&");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            model.addAttribute(matcher.group(1),matcher.group(3));
        }

        BooleanExpression exp = builder.build(Advert.class, "advert");
        Page<Advert> page;
        try {
             page = advertService.findAdvertsBySearchParams(exp, pageable);
        }catch(Exception e){
           return "redirect:" + URLS.ADVERTS_LIST;
        }

        int totalPages = page.getTotalPages();
        if (exp == null || totalPages == 0) {
            model.addAttribute("isListEmpty", true);
        }

        if (pageable.getPageNumber() > totalPages) {
            return "redirect:" + URLS.ADVERTS_LIST + "?page=0";
        }

        Pagination.paginate(page, model,request.getQueryString());
        List<Advert> adverts = page.getContent();
        model.addAttribute("adverts", adverts);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);
        return "advertsList";
    }

    @GetMapping("/animals")
    public String getAnimalsPageBySearchCriteria(@RequestParam(value = "title", required = false) String title,
                                                 @RequestParam(value = "breedId", required = false) String breedId,
                                                 @RequestParam(value = "gender", required = false) String gender,
                                                 @RequestParam(value = "readyToCopulation", required = false) String readyToCopulation,
                                                 @RequestParam(value = "property", required = false, defaultValue = "id") String property,
                                                 @RequestParam(value = "order", required = false, defaultValue = "DESC") String order,
                                                 @RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                                 HttpServletRequest request,
                                                 Model model) {
        Pageable pageable = new PageRequest(pageNumber, 15, new Sort(Sort.Direction.fromString(order), property));

        String searchParams = "";
        if(title != null && !title.equals("")){
            searchParams = "name=" + title + "&";
            model.addAttribute("title",title);
        }

        if(breedId != null && !breedId.equals("")){
            searchParams = searchParams + "breedId=" + breedId + "&";
            Breeds breed = breedService.find(Integer.valueOf(breedId));
            if(breed != null){
                model.addAttribute("breedName", breed.getName());
            }
        }

        String fullSearch = null;
        if(!searchParams.equals("")){
            fullSearch = searchParams;
        }

        if (fullSearch == null) {
            return "redirect:" + URLS.ANIMALS_LIST;
        }

        PredicateBuilder<Animal> builder = new PredicateBuilder<>();
        Pattern pattern = Pattern.compile("(\\w+?)(=|<|>)([\\p{L}\\s.,!0-9]+)&");
        Matcher matcher = pattern.matcher(fullSearch + "&");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        BooleanExpression exp = builder.build(Animal.class, "animal");
        Page<Animal> page = animalService.findAnimalsBySearchParams(exp, pageable);
        int totalPages = page.getTotalPages();
        if (exp == null || totalPages == 0) {
            model.addAttribute("isListEmpty", true);
            return "animalsList";
        }

        if (pageable.getPageNumber() > totalPages) {
            return "redirect:" + URLS.ANIMALS_LIST;
        }

        Pagination.paginate(page, model,request.getQueryString());
        List<Animal> animals = page.getContent();
        model.addAttribute("animals", animals);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);
        return "animalsList";
    }
}
