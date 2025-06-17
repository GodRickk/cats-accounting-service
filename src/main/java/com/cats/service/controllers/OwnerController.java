package com.cats.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.cats.service.dto.OwnerDto;
import com.cats.service.enums.Role;
import com.cats.service.services.OwnerServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerServiceImpl ownerService;

    @GetMapping("/all")
    public ResponseEntity AllOwners() {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                return ResponseEntity.ok(ownerService.getAllOwners());
            } else {
                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/cats/ownerid")
    public ResponseEntity CatsByOwnerId(@RequestParam(value = "ownerid") Integer ownerId) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                return ResponseEntity.ok(ownerService.getCatsByOwnerId(ownerId));
            } else {
                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody OwnerDto ownerDto) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                ownerService.save(ownerDto);
                return ResponseEntity.ok("Owner successfully saved.");
            } else {
                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/owner/id")
    public ResponseEntity ById(@RequestParam(value = "id") Integer id) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                return ResponseEntity.ok(ownerService.findById(id));
            } else {
                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/id")
    public ResponseEntity delete(@RequestParam(value = "id") Integer id) {
        try {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority(Role.Admin.toString()))){
                ownerService.delete(id);
                return ResponseEntity.ok("Owner successfully deleted.");
            } else {
                return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
