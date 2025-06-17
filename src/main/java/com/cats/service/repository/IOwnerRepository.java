package com.cats.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.cats.service.entity.Owner;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface IOwnerRepository extends JpaRepository<Owner, Integer>, JpaSpecificationExecutor<Owner> {

}
