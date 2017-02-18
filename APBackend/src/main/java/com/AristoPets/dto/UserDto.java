package com.AristoPets.dto;

import com.AristoPets.entity.Advert;
import com.AristoPets.entity.Animal;
import com.AristoPets.entity.enums.AuthType;
import com.AristoPets.entity.enums.UserType;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private long id;
    private UserType userType;
    private String nursery;
    private String authType;
    private String authId;
    private String phoneNumber;
    private String club;
    //TODO:Separate socials into different types(FB,VK,SKYPE)
    private String socials;
    private String photo;
    private boolean contractOfSale;
    private List<Animal> animals;
    private List<Advert> adverts;
    private String city;
    private String country;

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNursery() {
        return nursery;
    }

    public void setNursery(String nursery) {
        this.nursery = nursery;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getSocials() {
        return socials;
    }

    public void setSocials(String socials) {
        this.socials = socials;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isContractOfSale() {
        return contractOfSale;
    }

    public void setContractOfSale(boolean contractOfSale) {
        this.contractOfSale = contractOfSale;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }
}
