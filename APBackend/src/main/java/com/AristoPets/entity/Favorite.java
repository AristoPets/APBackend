package com.AristoPets.entity;


import javax.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    private long id;

    @Column(name = "advert_id",updatable = false, insertable = false)
    private Long advertId;

    @Column(name = "animal_id",updatable = false, insertable = false)
    private Long animalId;

    @Column(name = "user_id")
    private long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advert_id")
    private Advert advert;


    public Favorite(){}

    public Favorite(long id, Long advertId, Long animalId, long userId) {
        this.id = id;
        this.advertId = advertId;
        this.animalId = animalId;
        this.userId = userId;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public Long getAdvertId() { return advertId; }

    public void setAdvertId(Long advertId) { this.advertId = advertId; }

    public Long getAnimalId() { return animalId; }

    public void setAnimalId(Long animalId) { this.animalId = animalId; }

    public long getUserId() { return userId; }

    public void setUserId(long userId) { this.userId = userId; }

    public Animal getAnimal() { return animal; }

    public void setAnimal(Animal animal) { this.animal = animal; }

    public Advert getAdvert() { return advert; }

    public void setAdvert(Advert advert) { this.advert = advert; }
}
