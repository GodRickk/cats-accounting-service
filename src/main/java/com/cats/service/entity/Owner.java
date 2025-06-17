package com.cats.service.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owner", schema = "public", catalog = "postgres")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id", nullable = false)
    private Integer ownerId;
    @Column(name = "name")
    private String name;
    @Column(name = "birth_date")
    //private Date birthDate;
    private String birthDate;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cat> catList;

    public Owner() {
    }

    public Owner(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.catList = new ArrayList<Cat>();
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
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

    public void setCatList(List<Cat> catList) {
        this.catList = catList;
    }

    public List<Cat> getCatList() {
        return catList;
    }

    public void addCat(Cat cat) {
        catList.add(cat);
    }

    public void removeCat(Cat cat) {
        catList.remove(cat);
    }
}