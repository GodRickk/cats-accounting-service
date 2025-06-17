package com.cats.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.cats.service.dto.CatDto;
import com.cats.service.enums.Color;
import com.cats.service.enums.Role;
import com.cats.service.services.CatServiceImpl;
import com.cats.service.services.UserServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("/cat")
public class CatController {

    @Autowired
    private CatServiceImpl catService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/all")
    public ResponseEntity<Object> AllCats() {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                return ResponseEntity.ok(catService.getAllCats());
            } else {
                return ResponseEntity.ok(userService.getAllCats());
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/id")
    public ResponseEntity<Object> ById(@RequestParam(value = "id") Integer id) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                return ResponseEntity.ok(catService.findById(id));
            } else {
                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/owner/catid")
    public ResponseEntity<Object> OwnerByCatId(@RequestParam(value = "catid") Integer id) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                return ResponseEntity.ok(catService.findOwnerByCatId(id));
            } else {
                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/color")
    public ResponseEntity<Object> CatsWithCatColor(@RequestParam(value = "color") Color color) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                return ResponseEntity.ok(catService.getCatsWithCatColor(color));
            } else {
                return ResponseEntity.ok(userService.getCatsWithCatColor(color));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody CatDto catDto) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                catService.save(catDto);
                return ResponseEntity.ok("Cat successfully saved.");
            } else {
                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/set/ownerid/catid")
    public ResponseEntity<Object> setOwnerById(@RequestParam(value = "catid") Integer catId, @RequestParam(value = "ownerid") Integer ownerId) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                catService.setOwnerById(catId, ownerId);
                return ResponseEntity.ok("Successfully set new owner.");
            } else {
                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/id")
    public ResponseEntity<Object> delete(@RequestParam(value = "id") Integer id) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                catService.delete(id);
                return ResponseEntity.ok("Cat successfully deleted.");
            } else {
                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}