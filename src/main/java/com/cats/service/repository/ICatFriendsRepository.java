package com.cats.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.cats.service.entity.CatFriends;

import java.util.List;

@Repository
public interface ICatFriendsRepository extends JpaRepository<CatFriends, Integer> {
    List<CatFriends> findByCatId1OrCatId2(Integer Cat1Id, Integer Cat2Id);
}
