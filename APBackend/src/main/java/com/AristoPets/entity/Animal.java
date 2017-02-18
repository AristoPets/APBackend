package com.AristoPets.entity;


import com.AristoPets.entity.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "animals")
@JsonFilter("parentsFilter")
public class Animal {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "registered_name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "color")
    private String color;

    @Column(name = "birthday")
    @Temporal(value = TemporalType.DATE)
    private Date birthday;

    @Column(name = "club")
    private String club;

    @Column(name = "nursery")
    private String nursery;

    @Column(name = "more_info")
    private String moreInfo;

    @ManyToOne
    @JoinColumn(name = "breed_id", nullable = false)
    private Breeds breed;

    @Column(name = "breed_id", updatable = false, insertable = false)
    private int breedId;

    @Column(name = "ready_to_copulation", nullable = false)
    private boolean readyToCopulation;

    @JsonIgnore
    @Column(name = "archived")
    private boolean archived;

    @Column(name = "user_id", updatable = false, insertable = false)
    private long userId;

    @Basic
    private ArrayList<String> photos;

    @Basic
    private ArrayList<String> titles;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Animal() {
    }

    public Animal(String name, Gender gender, String color, Date birthday,
                  String club, String moreInfo, Breeds breed, User user, ArrayList<String> photo,
                  boolean readyToCopulation, long userId) {
        this.name = name;
        this.gender = gender;
        this.color = color;
        this.birthday = birthday;
        this.club = club;
        this.moreInfo = moreInfo;
        this.breed = breed;
        this.user = user;
        this.photos = photo;
        this.readyToCopulation = readyToCopulation;
        this.userId = userId;
    }

    public boolean isReadyToCopulation() {
        return readyToCopulation;
    }

    public void setCopulationStatus(boolean isReady) {
        readyToCopulation = isReady;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.birthday = dateFormat.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public Breeds getBreed() {
        return breed;
    }

    public void setBreed(Breeds breed) {
        this.breed = breed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNursery() {
        return nursery;
    }

    public void setNursery(String nursery) {
        this.nursery = nursery;
    }

    public ArrayList<String>  getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    public int getBreedId() { return breedId; }

    public void setBreedId(int breedId) { this.breedId = breedId; }
}
