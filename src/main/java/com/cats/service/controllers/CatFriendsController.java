package com.cats.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.cats.service.dto.CatFriendsDto;
import com.cats.service.enums.Role;
import com.cats.service.services.CatFriendsServiceImpl;
import com.cats.service.services.UserServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("/friends")
public class CatFriendsController {
    @Autowired
    private CatFriendsServiceImpl catFriendsPairService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody CatFriendsDto catFriendsPairDto) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                catFriendsPairService.save(catFriendsPairDto);
            } else {
                userService.saveCatFriendsPair(catFriendsPairDto);
            }
            return ResponseEntity.ok("Cat Friends Pair successfully saved.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/id")
    public ResponseEntity<Object> ById(@RequestParam(value = "id") Integer catFriendsId) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                return ResponseEntity.ok(catFriendsPairService.findById(catFriendsId));
            } else {
                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/id/cat-friends")
    public ResponseEntity<Object> FriendsByCatId(@RequestParam(value = "id") Integer catId) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                return ResponseEntity.ok(catFriendsPairService.getFriendsByCatId(catId));
            } else {
                return ResponseEntity.ok(userService.getFriendsByCatId(catId));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/id")
    public ResponseEntity<Object> delete(@RequestParam(value = "id") Integer catFriendsId) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                catFriendsPairService.delete(catFriendsId);
            } else {
                userService.deleteCatFriendsPair(catFriendsId);
            }
            return ResponseEntity.ok("Cat-Friends pair successfully deleted.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> AllCatFriendsPairs() {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                return ResponseEntity.ok(catFriendsPairService.getAll());
            } else {
                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}