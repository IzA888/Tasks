package com.example.Tasks.Controller.factory;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.Tasks.DTO.UserDto;
import com.example.Tasks.Model.User;

@Component
public class UserFactory {
    
    public UserDto toDto(User user) {
        UserDto dto = new UserDto();

        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;
    }

    public User toEntity(UserDto dto) {
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserDto toDto(Optional<User> user) {
        UserDto dto = new UserDto();

        dto.setUsername(user.get().getUsername());
        dto.setPassword(user.get().getPassword());
        return dto;
    }
}
