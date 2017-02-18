package com.AristoPets.entity;


import com.AristoPets.entity.enums.AuthType;
import com.AristoPets.entity.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "user_type")
    @Enumerated(EnumType.ORDINAL)
    private UserType userType;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "nursery")
    private String nursery;

    @Column(name = "auth_type")
    @Enumerated(EnumType.ORDINAL)
    private AuthType authType;

    @Column(name = "auth_id")
    private String authId;

    @Email
    @Column(name = "email")
    private String email;

    @Size(min = 10)
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "club")
    private String club;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "socials")
    private String socials;

    @Column(name = "photo")
    private String photo;

    @Column(name = "contract_of_sale")
    private boolean contractOfSale;

    @JsonIgnore
    @Column(name = "archived")
    private boolean archived;

    public User() {
    }

    public User(UserType userType, String firstName, String lastName, String nursery, AuthType authType,
                String authId, String email, String phoneNumber, String club, String socials,
                String photo, boolean contractOfSale, boolean archived) {
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nursery = nursery;
        this.authType = authType;
        this.authId = authId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.club = club;
        this.socials = socials;
        this.photo = photo;
        this.contractOfSale = contractOfSale;
        this.archived = archived;
    }

    public long getId() {
        return id;
    }

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
        return authType.toString();
    }

    public void setAuthType(AuthType authType) {
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

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }
}