package com.cats.service.dto;

import com.cats.service.entity.Cat;
import com.cats.service.entity.Owner;

import java.util.List;

public class OwnerDto {
    private String name;

    private String birthDate;

    private List<Cat> catList;

    private Integer id;

    public OwnerDto(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public OwnerDto(Owner owner) {
        name = owner.getName();
        birthDate = owner.getBirthDate();
        catList = owner.getCatList();
        id = owner.getOwnerId();
    }

    public void setCatList(List<Cat> catList) {
        this.catList = catList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public List<Cat> getCatList() {
        return catList;
    }

    public String getName() {
        return name;
    }


}