package com.AristoPets.entity;

import com.AristoPets.entity.enums.AnimalType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "breeds")
public class Breeds implements Serializable {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "animal_type")
    @Enumerated
    private AnimalType animalType;

    public Breeds() {
    }

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

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }
}