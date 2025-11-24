package com.example.Tasks.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Tasks.DTO.UserDto;

import com.example.Tasks.Controller.factory.UserFactory;
import com.example.Tasks.Services.UserService;


@RestController
public class UserController {

    private UserFactory factory;

    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public ResponseEntity<UserDto> createUser(@Validated @RequestBody UserDto user){
        return ResponseEntity.status(HttpStatus.CREATED).body(factory.toDto(userService.createUser(factory.toEntity(user))));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(factory.toDto(userService.getById(id)));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Validated @RequestBody UserDto user) {
       return  ResponseEntity.ok().body(factory.toDto(userService.updateUser(id, factory.toEntity(user))));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
       return ResponseEntity.ok("Apagado");
    }
}
