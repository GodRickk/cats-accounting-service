package com.cats.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cats.service.dto.CatDto;
import com.cats.service.dto.OwnerDto;
import com.cats.service.entity.Cat;
import com.cats.service.entity.Owner;
import com.cats.service.repository.ICatRepository;
import com.cats.service.repository.IOwnerRepository;
import com.cats.service.services.interfaces.IOwnerService;

import java.util.ArrayList;
import java.util.List;


@Service
public class OwnerServiceImpl implements IOwnerService {

    @Autowired
    private IOwnerRepository ownerRepository;

    @Autowired
    private ICatRepository catRepository;

    @Override
    public OwnerDto findById(Integer id) {
        return new OwnerDto(ownerRepository.findById(id).orElseThrow().getName(), ownerRepository.findById(id).orElseThrow().getBirthDate());
    }

    @Override
    public void save(OwnerDto ownerDto) {
        Owner owner = new Owner(ownerDto.getName(), ownerDto.getBirthDate());
        if (ownerDto.getCatList() != null) {
            owner.setCatList(ownerDto.getCatList());
        }
        if (ownerDto.getId() != null) {
            owner.setOwnerId(ownerDto.getId());
        }
        ownerRepository.save(owner);
    }

    @Override
    public void delete(Integer id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        return ownerListToOwnerDtoList(ownerRepository.findAll());
    }

    @Override
    public List<CatDto> getCatsByOwnerId(Integer ownerId) {
        List<Cat> allCatList = catRepository.findAll();
        List<Cat> ownerCatList = new ArrayList<>();
        for (Cat cat : allCatList) {
            if (cat.getOwner().getOwnerId().equals(ownerId)) {
                ownerCatList.add(cat);
            }
        }
        return catListToCatDtoList(ownerCatList);
    }

    private List<CatDto> catListToCatDtoList(List<Cat> cats) {
        ArrayList<CatDto> catDtoList = new ArrayList<CatDto>();
        for (Cat cat : cats) {
            CatDto catDto = new CatDto(cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor());
            catDtoList.add(catDto);
        }
        return catDtoList;
    }

    private List<OwnerDto> ownerListToOwnerDtoList(List<Owner> ownerList) {
        ArrayList<OwnerDto> ownerDtoList = new ArrayList<OwnerDto>();
        for (Owner owner : ownerList) {
            OwnerDto ownerDto = new OwnerDto(owner.getName(), owner.getBirthDate());
            ownerDtoList.add(ownerDto);
        }
        return ownerDtoList;
    }
}
