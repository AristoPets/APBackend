package com.AristoPets.dto;


import com.AristoPets.entity.AdvertItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AdvertDto implements Serializable {
    private long id;
    private String birthday;
    private String title;
    private String fParentRef;
    private String mParentRef;
    private long fParentId;
    private long mParentId;
    private long breedId;
    private String club;
    private String description;
    private String firstVaccination;
    private String secondVaccination;
    private long userId;
    private List<AdvertItem> advertItems;
    private ArrayList<String> photos;


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) { this.birthday = birthday; }

    public String getfParentRef() {
        return fParentRef;
    }

    public void setfParentRef(String fParentRef) {
        this.fParentRef = fParentRef;
    }

    public String getmParentRef() {
        return mParentRef;
    }

    public void setmParentRef(String mParentRef) {
        this.mParentRef = mParentRef;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirstVaccination() {
        return firstVaccination;
    }

    public void setFirstVaccination(String firstVaccination) {
        this.firstVaccination = firstVaccination;
    }

    public String getSecondVaccination() {
        return secondVaccination;
    }

    public void setSecondVaccination(String secondVaccination) {
        this.secondVaccination = secondVaccination;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<AdvertItem> getAdvertItems() {
        return advertItems;
    }

    public void setAdvertItems(List<AdvertItem> advertItems) {
        this.advertItems = advertItems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getfParentId() { return fParentId; }

    public void setfParentId(long fParentId) { this.fParentId = fParentId; }

    public long getmParentId() { return mParentId; }

    public void setmParentId(long mParentId) { this.mParentId = mParentId; }

    public ArrayList<String> getPhotos() { return photos; }

    public void setPhotos(ArrayList<String> photos) { this.photos = photos; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title;}

    public long getBreedId() { return breedId; }

    public void setBreedId(long breedId) { this.breedId = breedId; }
}
