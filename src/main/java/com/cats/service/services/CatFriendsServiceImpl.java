package com.cats.service.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cats.service.dto.CatDto;
import com.cats.service.dto.CatFriendsDto;
import com.cats.service.entity.Cat;
import com.cats.service.entity.CatFriends;
import com.cats.service.repository.ICatFriendsRepository;
import com.cats.service.repository.ICatRepository;
import com.cats.service.services.interfaces.ICatFriendsService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatFriendsServiceImpl implements ICatFriendsService {

    @Autowired
    private ICatFriendsRepository catFriendsRepository;

    @Autowired
    private ICatRepository catRepository;

    @Override
    public CatFriendsDto findById(Integer id) {
        return new CatFriendsDto(catFriendsRepository.findById(id).orElseThrow().getCatId1(), catFriendsRepository.findById(id).orElseThrow().getCatId2());
    }

    @Override
    public void save(CatFriendsDto catFriendsDto) {
        CatFriends catFriends = new CatFriends(catFriendsDto.getCatId1(), catFriendsDto.getCatId2());
        if (catFriendsDto.getId() != null) {
            catFriends.setId(catFriendsDto.getId());
        }
        catFriendsRepository.save(catFriends);
    }

    @Override
    public void delete(Integer catFriendsId) {
        catFriendsRepository.deleteById(catFriendsId);
    }

    @Override
    public List<CatFriendsDto> getAll() {
        return catFriendsListToCatFriendsDtoList(catFriendsRepository.findAll());
    }


    @Override
    public List<CatDto> getFriendsByCatId(Integer catId) {
        List<CatFriends> catFriendsList = catFriendsRepository.findAll();
        List<Integer> catFriendsIds = new ArrayList<>();
        for (CatFriends catFriends : catFriendsList) {
            if (catFriends.getCatId1().equals(catId)) {
                catFriendsIds.add(catFriends.getCatId2());
            }
            if (catFriends.getCatId2().equals(catId)) {
                catFriendsIds.add(catFriends.getCatId1());
            }
        }
        List<Cat> catFriends = catRepository.findAllById(catFriendsIds);
        return catListToCatDtoList(catFriends);
    }

    private List<CatFriendsDto> catFriendsListToCatFriendsDtoList(List<CatFriends> catFriendsPairs) {
        ArrayList<CatFriendsDto> catFriendsList = new ArrayList<CatFriendsDto>();
        for (CatFriends catFriends : catFriendsPairs) {
            CatFriendsDto catFriendsPairDto = new CatFriendsDto(catFriends.getCatId1(), catFriends.getCatId2());
            catFriendsList.add(catFriendsPairDto);
        }
        return catFriendsList;
    }

    private List<CatDto> catListToCatDtoList(List<Cat> catsList) {
        ArrayList<CatDto> catDtoList = new ArrayList<CatDto>();
        for (Cat cat : catsList) {
            CatDto catDto = new CatDto(cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor());
            catDtoList.add(catDto);
        }

        return catDtoList;
    }

}