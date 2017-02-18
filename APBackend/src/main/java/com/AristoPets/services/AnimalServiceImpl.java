package com.AristoPets.services;

import com.AristoPets.dao.AnimalRepository;
import com.AristoPets.dao.BreedsRepository;
import com.AristoPets.dao.UserRepository;
import com.AristoPets.dto.AnimalDto;
import com.AristoPets.entity.Animal;
import com.AristoPets.entity.enums.Gender;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final BreedsRepository breedsRepository;
    private final UserRepository userRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository, BreedsRepository breedsRepository, UserRepository userRepository) {
        this.animalRepository = animalRepository;
        this.breedsRepository = breedsRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Animal> findByUserId(long id) {
        return animalRepository.findByUserID(id);
    }

    @Override
    public Animal saveAndFlush(Animal animal) {
        return animalRepository.saveAndFlush(animal);
    }

    @Override
    public Animal saveAndFlush(AnimalDto animalDto) {
        Animal animal = mapAnimalDtoIntoEntity(animalDto);
        return animalRepository.saveAndFlush(animal);
    }

    @Override
    public Animal update(AnimalDto animalDto) {
        Animal animal = mapAnimalDtoIntoEntity(animalDto);
        return animalRepository.save(animal);
    }

    @Override
    public Animal delete(long animalId, long userId) throws IllegalAccessException {
        Animal animal = findAnimal(animalId);
        if (animal == null) {
            return null;
        }
        if (animal.getUserId() != userId) {
            throw new IllegalAccessException("Access denied.User has no permissions");
        }
        int code = animalRepository.deleteAnimal(animalId);
        return animal;
    }

    @Override
    public Page<Animal> findAnimalsForCatalog(Pageable pageable) {
        return animalRepository.findAllAnimals(pageable);
    }

    @Override
    public Animal findAnimal(long id) {
        return animalRepository.findAnimal(id);
    }

    @Override
    public Page<Animal> findAnimalsBySearchParams(Predicate predicate, Pageable pageable) {
        return animalRepository.findAll(predicate, pageable);
    }

    private Animal mapAnimalDtoIntoEntity(AnimalDto animalDto) {
        Animal animal;
        long animalId = animalDto.getId();
        if (animalId != 0) {
            animal = animalRepository.findOne(animalId);
        } else {
            animal = new Animal();
        }
        animal.setBreed(breedsRepository.findOne(animalDto.getBreedId()));
        animal.setBirthday(animalDto.getBirthday());
        animal.setClub(animalDto.getClub());
        animal.setGender(animalDto.getGender().toLowerCase().equals("male") ? Gender.MALE : Gender.FEMALE);
        animal.setColor(animalDto.getColor());
        animal.setName(animalDto.getName());
        animal.setUser(userRepository.findOne(animalDto.getUserId()));
        // TODO:titles - with or without photo, entity or array of Strings?
        ArrayList<String> titlesDto = animalDto.getTitles();
        if (titlesDto != null) {
            animal.setTitles(titlesDto);
        }
        animal.setNursery(animalDto.getNursery());
        animal.setMoreInfo(animalDto.getMoreInfo());
        animal.setCopulationStatus(animalDto.isReadyToCopulation());
        ArrayList<String> photos = animalDto.getPhotos();
        if (photos == null || photos.size() == 0) {
            photos = new ArrayList<>();
            photos.add("/img/noAnimal.jpg");
        }
        animal.setPhotos(photos);
        return animal;
    }
}
