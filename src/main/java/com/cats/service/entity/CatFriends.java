package com.cats.service.entity;

import javax.persistence.*;

@Entity
@Table(name = "cat_friends", schema = "public", catalog = "postgres")
public class CatFriends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "cat_id1")
    private Integer catId1;

    @Column(name = "cat_id2")
    private Integer catId2;

    public CatFriends() {
    }

    public CatFriends(Integer cat_id1, Integer cat_id2) {
        this.catId1 = cat_id1;
        this.catId2 = cat_id2;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCatId1() {
        return catId1;
    }

    public Integer getCatId2() {
        return catId2;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}