package com.AristoPets.services;


import com.AristoPets.dao.AnimalRepository;
import com.AristoPets.entity.Animal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ParentsService {

    private final AnimalRepository animalRepository;

    @Autowired
    public ParentsService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    private static final String[] INCLUDE_PROPERTIES = {"id","name","breed","gender", "photos"};
    private static final Set<String> excludeProperties = new HashSet<>(Arrays.asList(INCLUDE_PROPERTIES));

    public String getAdvertsParents(){
        List<Animal> animals = animalRepository.findAllAnimals();
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("parentsFilter", SimpleBeanPropertyFilter.filterOutAllExcept(excludeProperties));
        ObjectWriter writer = mapper.writer(filters);
        String parents;
        try {
            parents = writer.writeValueAsString(animals);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            parents = "Can't serialize animals to parents";
        }
        return parents;
    }
}
