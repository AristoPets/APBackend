package com.AristoPets.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "adverts")
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Size(min = 5, max = 70)
    @Column(name = "title")
    private String title;

    @Column(name = "f_parent_ref")
    private String fParentRef;

    @Column(name = "m_parent_ref")
    private String mParentRef;

    @Column(name = "f_parent_id")
    private long fParentId;

    @Column(name = "m_parent_id")
    private long mParentId;

    @Column(name = "average_price")
    private int averagePrice;

    @ManyToOne
    @JoinColumn(name = "breed_id", nullable = false)
    private Breeds breed;

    @Column(name = "breed_id", updatable = false, insertable = false)
    private int breedId;

    @Column(name = "user_id", updatable = false, insertable = false)
    private long userId;

    @Column(name = "club")
    private String club;

    @Column(name = "description")
    private String description;

    @Column(name = "f_vaccination")
    private String firstVaccination;

    @Column(name = "s_vaccination")
    private String secondVaccination;

    @Basic
    private ArrayList<String> photos;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "advert")
    private List<AdvertItem> advertItems;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @Column(name = "archived")
    private boolean archived;

    //TODO:
    @Transient
    private String placeOfBirth;


    public Advert() {
    }

    public Advert(Date birthday, String fParentRef, String mParentRef, long fParentId, Breeds breed,
                  long mParentId, String club, String description, String firstVaccination, String title,
                  String secondVaccination, User user, boolean archived, List<AdvertItem> advertItems, String placeOfBirth) {
        this.birthday = birthday;
        this.fParentRef = fParentRef;
        this.mParentRef = mParentRef;
        this.fParentId = fParentId;
        this.mParentId = mParentId;
        this.club = club;
        this.description = description;
        this.firstVaccination = firstVaccination;
        this.secondVaccination = secondVaccination;
        this.user = user;
        this.archived = archived;
        this.advertItems = advertItems;
        this.placeOfBirth = placeOfBirth;
        this.title = title;
        this.breed = breed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBirthday(String birthday) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.birthday = dateFormat.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<AdvertItem> getAdvertItems() {
        return advertItems;
    }

    public void setAdvertItems(List<AdvertItem> advertItems) {
        this.advertItems = advertItems;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public long getfParentId() {
        return fParentId;
    }

    public void setfParentId(long fParentId) {
        this.fParentId = fParentId;
    }

    public long getmParentId() {
        return mParentId;
    }

    public void setmParentId(long mParentId) {
        this.mParentId = mParentId;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Breeds getBreed() {
        return breed;
    }

    public void setBreed(Breeds breed) {
        this.breed = breed;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getAveragePrice() { return averagePrice; }

    public void setAveragePrice(int averagePrice) { this.averagePrice = averagePrice; }
}

