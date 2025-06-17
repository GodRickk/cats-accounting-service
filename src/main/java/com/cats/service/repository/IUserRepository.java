package com.cats.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cats.service.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT user FROM User user WHERE user.username = :username")
    User getUserByUsername(@Param("username") String username);
}
