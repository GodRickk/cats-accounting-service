package com.cats.service.dto;

import com.cats.service.entity.CatFriends;

public class CatFriendsDto {
    private Integer id;

    private final Integer catId1;

    private final Integer catId2;

    public CatFriendsDto(Integer catId1, Integer catId2) {
        this.catId1 = catId1;
        this.catId2 = catId2;
    }

    public CatFriendsDto(CatFriends catFriends) {
        this.catId1 = catFriends.getCatId1();
        this.catId2 = catFriends.getCatId2();
        this.id = catFriends.getId();
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
}
