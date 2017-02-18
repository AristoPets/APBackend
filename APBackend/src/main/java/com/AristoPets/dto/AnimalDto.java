package com.AristoPets.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AnimalDto implements Serializable {
    private long id;
    private String name;
    private String gender;
    private String color;
    private String birthday;
    private String club;
    private String nursery;
    private String moreInfo;
    private long breedId;
    private long userId;
    private boolean readyToCopulation;
    private ArrayList<String> titles;
    private ArrayList<String> photos;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getNursery() {
        return nursery;
    }

    public void setNursery(String nursery) {
        this.nursery = nursery;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public long getBreedId() {
        return breedId;
    }

    public void setBreedId(long breedId) {
        this.breedId = breedId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public ArrayList<String> getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public ArrayList<String> getPhotos() { return photos; }

    public void setPhotos(ArrayList<String> photos) { this.photos = photos; }

    public boolean isReadyToCopulation() {
        return readyToCopulation;
    }

    public void setReadyToCopulation(boolean readyToCopulation) {
        this.readyToCopulation = readyToCopulation;
    }
}
