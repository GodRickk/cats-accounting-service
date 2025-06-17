package com.cats.service.services.interfaces;

import com.cats.service.dto.UserDto;

import java.util.List;

public interface IUserStorageService {

        void saveUser(UserDto userDto);

        void deleteUser(Integer userId);

        List<UserDto> getAllUsers();

        void setOwnerToUser(Integer userId, Integer ownerId);
}
