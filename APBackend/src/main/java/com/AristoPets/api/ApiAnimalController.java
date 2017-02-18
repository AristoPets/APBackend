package com.AristoPets.api;


import com.AristoPets.dto.AnimalDto;
import com.AristoPets.entity.Animal;
import com.AristoPets.entity.User;
import com.AristoPets.entity.enums.UserType;
import com.AristoPets.services.AnimalService;
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
@RequestMapping("/api/animal")
public class ApiAnimalController {

    private AnimalService animalService;
    private UserService userService;

    @Autowired
    public ApiAnimalController(AnimalService animalService, UserService userService) {
        this.animalService = animalService;
        this.userService = userService;
    }


    @PostMapping("/")
    public ResponseEntity<?> createAnimal(@RequestBody AnimalDto animalDto, HttpServletRequest request) {
        User user = (User) request.getAttribute("userData");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        //TODO:validation of existing animal
        animalDto.setUserId(user.getId());
        Animal result = animalService.saveAndFlush(animalDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("animal/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnimal(@PathVariable(name = "id") long id, @RequestBody AnimalDto animalDto,
                                          HttpServletRequest request) {
        User user = (User) request.getAttribute("userData");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        long userId = user.getId();
        Animal existingAnimal = animalService.findAnimal(id);
        if (existingAnimal == null || existingAnimal.isArchived()) {
            return ResponseEntity.noContent().build();
        }
        if (!isUserBreeder(userId) && (existingAnimal.getUserId() != userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        animalDto.setId(id);
        animalDto.setUserId(userId);
        Animal updatedAnimal = animalService.update(animalDto);
        return ResponseEntity.ok(updatedAnimal);
    }

    @GetMapping("/")
    ResponseEntity<?> getUserAnimals(HttpServletRequest request) {
        User user = (User) request.getAttribute("userData");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<Animal> animals = animalService.findByUserId(user.getId());
        if (animals == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getAnimalById(@PathVariable(name = "id") long animalId) {
        Animal animal = animalService.findAnimal(animalId);
        if (animal == null || animal.isArchived()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(animal);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteAnimal(HttpServletRequest request, @PathVariable(name = "id") long animalId) {
        User user = (User) request.getAttribute("userData");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Animal animal;
        try {
            animal = animalService.delete(animalId, user.getId());
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if (animal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animal);
    }


    private boolean isUserBreeder(long userId) {
        return userService.getUser(userId).getUserType() == UserType.BREEDER;
    }
}
