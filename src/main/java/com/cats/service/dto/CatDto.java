package com.cats.service.dto;
import com.cats.service.entity.Cat;

import com.cats.service.entity.Owner;
import com.cats.service.enums.Color;

public class CatDto {

    private final String name;

    private final String birthDate;

    private final String breed;

    private Integer id;

    private final Color color;

    private Owner owner;

    public CatDto(String name, String birthDate, String breed, Color color) {
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
    }

    public CatDto(Cat cat) {
        id = cat.getId();
        name = cat.getName();
        birthDate = cat.getBirthDate();
        breed = cat.getBreed();
        color = cat.getColor();
        owner = cat.getOwner();
    }


    public Owner getOwner() {
        return owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getBreed() {
        return breed;
    }

    public Color getColor() {
        return color;
    }
}