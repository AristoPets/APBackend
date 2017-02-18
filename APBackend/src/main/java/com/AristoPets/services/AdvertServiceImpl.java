package com.AristoPets.services;


import com.AristoPets.dao.AdvertsRepository;
import com.AristoPets.dao.BreedsRepository;
import com.AristoPets.dao.UserRepository;
import com.AristoPets.dto.AdvertDto;
import com.AristoPets.entity.Advert;
import com.AristoPets.entity.AdvertItem;
import com.AristoPets.exceptions.WrongFieldInputException;
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
public class AdvertServiceImpl implements AdvertService {

    private final AdvertsRepository advertsRepository;
    private final UserRepository userRepository;
    private final BreedsRepository breedsRepository;

    @Autowired
    public AdvertServiceImpl(AdvertsRepository advertsRepository, UserRepository userRepository, BreedsRepository breedsRepository) {
        this.advertsRepository = advertsRepository;
        this.userRepository = userRepository;
        this.breedsRepository = breedsRepository;
    }


    @Override
    public List<Advert> findAll() {

        return advertsRepository.findAllAdverts();
    }

    @Override
    public Advert findOne(long id) {
        return advertsRepository.findOne(id);
    }

    @Override
    public List<Advert> findByUserId(long userId) {
        return advertsRepository.findByUserID(userId);
    }

    @Override
    public Advert create(AdvertDto advertDto) {
        Advert createdAdvert = mapAdvertDtoIntoEntity(advertDto);
        return advertsRepository.saveAndFlush(createdAdvert);
    }

    @Override
    public Advert update(AdvertDto advertDto) throws WrongFieldInputException {
        Advert updatedAdvert = mapAdvertDtoIntoEntity(advertDto);
        if (updatedAdvert == null) {
            throw new WrongFieldInputException("Advert item must have id to update itself");
        }
        return advertsRepository.save(updatedAdvert);
    }

    @Override
    public Advert delete(long advertId, long userId) throws IllegalAccessException {
        Advert advert = findOne(advertId);
        if (advert == null) {
            return null;
        }
        if (advert.getUserId() != userId) {
            throw new IllegalAccessException("Access denied.User has no permissions");
        }
        int code = advertsRepository.deleteAdvert(advertId);
        return advert;
    }

    @Override
    public Page<Advert> findAdvertsForCatalog(Pageable pageable) {
        return advertsRepository.findAllAdverts(pageable);
    }

    @Override
    public Page<Advert> findAdvertsBySearchParams(Predicate predicate, Pageable pageable) {
        return advertsRepository.findAll(predicate, pageable);
    }

    @Override
    public List<Advert> findByParentId(long parentId) {
        return advertsRepository.findByParentId(parentId);
    }

    @Override
    public Page<Advert> findAdvertsEmptySearch(Pageable pageable) {
        return advertsRepository.findAdvertsEmptySearch(pageable);
    }


    private Advert mapAdvertDtoIntoEntity(AdvertDto advertDto) {

        Advert advert;
        long advertId = advertDto.getId();
        if (advertId != 0) {
            advert = advertsRepository.findOne(advertId);
        } else {
            advert = new Advert();
        }

        ArrayList<String> photos = advertDto.getPhotos();
        if(photos == null || photos.size() == 0) {
            photos = new ArrayList<>();
            photos.add("/img/noAnimal.jpg");
        }
        advert.setPhotos(photos);

        List<AdvertItem> advertItems = advertDto.getAdvertItems();
        if (advert.getAdvertItems() != null) {
            for (AdvertItem adv : advertItems) {
                if (adv.getId() == 0) {
                    return null;
                }
            }
        }


        int priceSum = 0;
        for (AdvertItem advertItem : advertItems) {
            ArrayList<String> advertItemPhotos = advertItem.getPhotos();
            if(advertItemPhotos == null || advertItemPhotos.size() == 0){
                advertItemPhotos = new ArrayList<>();
                advertItemPhotos.add("/img/noAnimal.jpg");
                advertItem.setPhotos(advertItemPhotos);
            }
            advertItem.setAdvert(advert);
            priceSum += advertItem.getPrice();
        }
        advert.setAdvertItems(advertItems);
        if(advertItems.size() != 0) {
            advert.setAveragePrice(priceSum / advertItems.size());
        }else{
            advert.setAveragePrice(0);
        }

        //advertItems.forEach(advertItem -> advertItem.setAdvert(advert));
        advert.setBreed(breedsRepository.findOne(advertDto.getBreedId()));
        advert.setTitle(advertDto.getTitle());
        advert.setClub(advertDto.getClub());
        advert.setBirthday(advertDto.getBirthday());
        advert.setDescription(advertDto.getDescription());
        advert.setFirstVaccination(advertDto.getFirstVaccination());
        advert.setSecondVaccination(advertDto.getSecondVaccination());
        advert.setDescription(advertDto.getDescription());
        advert.setmParentRef(advertDto.getmParentRef());
        advert.setfParentRef(advertDto.getfParentRef());
        advert.setmParentId(advertDto.getmParentId());
        advert.setfParentId(advertDto.getfParentId());
        advert.setAdvertItems(advertItems);
        advert.setUser(userRepository.findOne(advertDto.getUserId()));
        return advert;
    }

}
