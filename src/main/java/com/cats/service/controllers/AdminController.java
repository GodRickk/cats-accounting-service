package com.cats.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cats.service.dto.UserDto;
import com.cats.service.services.UserStorageServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserStorageServiceImpl userStorageService;

    @PostMapping("/users/save")
    public ResponseEntity saveUser(@RequestBody UserDto userDto) {
        try {
            userStorageService.saveUser(userDto);
            return ResponseEntity.ok("User successfully saved.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/users/owner")
    public ResponseEntity setOwnerToUser(@RequestParam Integer userId, @RequestParam Integer ownerId) {
        try {
            userStorageService.setOwnerToUser(userId, ownerId);
            return ResponseEntity.ok("Owner successfully set to user.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/users/all")
    public ResponseEntity allUsers() {
        try {
            return ResponseEntity.ok(userStorageService.getAllUsers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/users/delete/id")
    public ResponseEntity deleteUser(@RequestParam(value = "id") Integer userId) {
        try {
            userStorageService.deleteUser(userId);
            return ResponseEntity.ok("User successfully deleted.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
