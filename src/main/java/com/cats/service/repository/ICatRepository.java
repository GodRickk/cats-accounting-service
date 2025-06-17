package com.cats.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cats.service.entity.Cat;
import com.cats.service.entity.Owner;
import com.cats.service.enums.Color;

import java.util.List;

@Repository
public interface ICatRepository extends JpaRepository<Cat, Integer> {

    List<Cat> findByColor(Color color);
    List<Cat> findByColorAndOwner(Color color, Owner owner);
    List<Cat> findByOwner(Owner owner);
}

