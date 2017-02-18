package com.AristoPets.api;


import com.AristoPets.services.ParentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ApiParentController {

    private ParentsService parentsService;

    @Autowired
    public ApiParentController(ParentsService parentsService) {
        this.parentsService = parentsService;
    }


    @GetMapping("/parents/")
    public String getParents(){
            return parentsService.getAdvertsParents();
    }
}
