package com.AristoPets.entity;

import com.AristoPets.entity.enums.AdvertItemState;
import com.AristoPets.entity.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "adv_items")
public class AdvertItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "state")
    private AdvertItemState state;

    @Column(name = "price")
    private int price;

    @Column(name = "color", nullable = false)
    private String color;

    @Basic
    private ArrayList<String> photos;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advert_id", nullable = false)
    private Advert advert;


    public AdvertItem() {
    }

    public AdvertItem(Gender gender, AdvertItemState state, int price, String color, ArrayList<String> photos, Advert advert) {
        this.gender = gender;
        this.state = state;
        this.price = price;
        this.color = color;
        this.photos = photos;
        this.advert = advert;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AdvertItemState getState() {
        return state;
    }

    public void setState(AdvertItemState state) {
        this.state = state;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }
}
