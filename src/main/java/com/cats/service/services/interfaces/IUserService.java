package com.cats.service.services.interfaces;

import com.cats.service.dto.CatDto;
import com.cats.service.dto.CatFriendsDto;
import com.cats.service.enums.Color;

import java.util.List;
public interface IUserService {

    List<CatDto> getAllCats();

    List<CatDto> getCatsWithCatColor(Color color);

    void saveCatFriendsPair(CatFriendsDto catFriends);

    List<CatDto> getFriendsByCatId(Integer catId);

    void deleteCatFriendsPair(Integer catFriendsId);

}
