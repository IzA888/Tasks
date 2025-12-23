package com.example.Tasks.Controller.factory;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.Tasks.DTO.LoginUserDto;
import com.example.Tasks.Model.User;

@Component
public class LoginUserFactory {
    
    public LoginUserDto toDto(User user) {
        LoginUserDto dto = new LoginUserDto();

        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;
    }

    public User toEntity(LoginUserDto dto) {
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

    public LoginUserDto toDto(Optional<User> user) {
        LoginUserDto dto = new LoginUserDto();
        
        dto.setUsername(user.get().getUsername());
        dto.setPassword(user.get().getPassword());
        return dto;
    }
}
