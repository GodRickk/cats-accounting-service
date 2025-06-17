package com.cats.service.services.interfaces;

import com.cats.service.dto.CatDto;
import com.cats.service.dto.OwnerDto;
import com.cats.service.enums.Color;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


public interface ICatService {
    List<CatDto> getAllCats();

    CatDto findById(Integer id);

    void save(CatDto cat);

    void setOwnerById(Integer catId, Integer ownerId);

    void delete(Integer id);

    OwnerDto findOwnerByCatId(Integer id);

    List<CatDto> getCatsWithCatColor(Color color);
}
