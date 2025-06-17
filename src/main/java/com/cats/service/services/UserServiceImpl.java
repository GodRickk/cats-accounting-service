package com.cats.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.cats.service.dto.CatDto;
import com.cats.service.dto.CatFriendsDto;
import com.cats.service.entity.Cat;
import com.cats.service.entity.CatFriends;
import com.cats.service.entity.Owner;
import com.cats.service.enums.Color;
import com.cats.service.repository.ICatFriendsRepository;
import com.cats.service.repository.ICatRepository;
import com.cats.service.services.interfaces.IUserService;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private ICatRepository icatRepository;

    @Autowired
    private ICatFriendsRepository icatFriendsRepository;

    @Override
    public List<CatDto> getAllCats() {
        Owner userOwner = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getOwner();
        List<Cat> allCats = icatRepository.findByOwner(userOwner);
        return catsToCatDtos(allCats);
    }

    @Override
    public List<CatDto> getCatsWithCatColor(Color color) {
        Owner userOwner = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getOwner();
        List<Cat> catList = icatRepository.findByColorAndOwner(color, userOwner);
        return catsToCatDtos(catList);
    }

    @Override
    public void saveCatFriendsPair(CatFriendsDto catFriendsPairDto) {
        CatFriends catFriendsPair = new CatFriends(catFriendsPairDto.getCatId1(), catFriendsPairDto.getCatId2());
        if (catFriendsPairDto.getId() != null) {
            catFriendsPair.setId(catFriendsPairDto.getId());
        }
        icatFriendsRepository.save(catFriendsPair);
    }

    @Override
    public List<CatDto> getFriendsByCatId(Integer catId) {
        List<CatFriends> catFriends = icatFriendsRepository.findByCatId1OrCatId2(catId, catId);
        List<Integer> catFriendsIds = catFriendsPairsToIds(catFriends, catId);
        List<Cat> catList = icatRepository.findAllById(catFriendsIds);
        return catsToCatDtos(catList);
    }

    @Override
    public void deleteCatFriendsPair(Integer catFriendsId) {
        icatFriendsRepository.deleteById(catFriendsId);
    }

    private List<CatDto> catsToCatDtos(List<Cat> catList) {
        ArrayList<CatDto> catDtoList = new ArrayList<CatDto>();
        for (Cat cat : catList) {
            CatDto catDto = new CatDto(cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor());
            catDto.setId(cat.getId());
            catDtoList.add(catDto);
        }
        return catDtoList;
    }

    private List<Integer> catFriendsPairsToIds(List<CatFriends> pairs, Integer id){
        List<Integer> idList = new ArrayList<>();
        for (CatFriends catFriendsPair : pairs) {
            if (catFriendsPair.getCatId1().equals(id)) {
                idList.add(catFriendsPair.getCatId2());
            }
            if (catFriendsPair.getCatId2().equals(id)) {
                idList.add(catFriendsPair.getCatId1());
            }
        }
        return idList;
    }
}