package com.cats.service.services.interfaces;

import com.cats.service.dto.CatDto;
import com.cats.service.dto.OwnerDto;

import java.util.List;

public interface IOwnerService {
    OwnerDto findById(Integer id);

    void save(OwnerDto ownerDto);

    void delete(Integer id);

    List<OwnerDto> getAllOwners();

    List<CatDto> getCatsByOwnerId(Integer ownerId);
}
