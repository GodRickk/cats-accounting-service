package com.cats.service.services.interfaces;

import com.cats.service.dto.CatDto;
import com.cats.service.dto.CatFriendsDto;

import java.util.List;

public interface ICatFriendsService {

        CatFriendsDto findById(Integer id);

        void save(CatFriendsDto catFriendsdto);

        void delete(Integer catFriendsId);

        List<CatFriendsDto> getAll();

        List<CatDto> getFriendsByCatId(Integer catId);
}
