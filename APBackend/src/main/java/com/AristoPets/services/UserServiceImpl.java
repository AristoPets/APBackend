package com.AristoPets.services;

import com.AristoPets.dao.AdvertsRepository;
import com.AristoPets.dao.AnimalRepository;
import com.AristoPets.dao.UserRepository;
import com.AristoPets.dto.UserDto;
import com.AristoPets.entity.Advert;
import com.AristoPets.entity.Animal;
import com.AristoPets.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    private final AdvertsRepository advertsRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AnimalRepository animalRepository, AdvertsRepository advertsRepository) {
        this.userRepository = userRepository;
        this.animalRepository = animalRepository;
        this.advertsRepository = advertsRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getUserByAuthId(String authId) {
        return userRepository.findByAuthId(authId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDto getUserWithFullInfo(long userId) {
        User user = userRepository.findOne(userId);
        UserDto userDto = new UserDto();
        List<Animal> animalList = animalRepository.findByUserID(userId);
        List<Advert> advertList = advertsRepository.findByUserID(userId);
        userDto.setAdverts(advertList);
        userDto.setAnimals(animalList);
        userDto.setAuthId(user.getAuthId());
        userDto.setAuthType(user.getAuthType());
        userDto.setClub(user.getClub());
        userDto.setContractOfSale(user.isContractOfSale());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setId(user.getId());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setNursery(user.getNursery());
        userDto.setPhoto(user.getPhoto());
        userDto.setSocials(user.getSocials());
        userDto.setUserType(user.getUserType());

        return userDto;
    }

    @Override
    public User update(UserDto userDto) {
        User user = userRepository.findOne(userDto.getId());
        user.setSocials(userDto.getSocials());
        user.setUserType(userDto.getUserType());
        user.setPhoto(userDto.getPhoto());
        user.setNursery(userDto.getNursery());
        user.setClub(userDto.getClub());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setContractOfSale(userDto.isContractOfSale());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setCity(userDto.getCity());
        user.setCountry(userDto.getCountry());
        userRepository.saveAndFlush(user);
        return user;
    }

}
