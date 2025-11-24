package com.example.Tasks.Services.Interface;

import java.util.Optional;

import com.example.Tasks.Model.User;

public interface IUserService {
    
    User createUser(User user);
    User updateUser(Long id, User user);
    Optional<User> getById(Long id);
    void deleteUser(Long id);
    Optional<User> getByUsername(String username);
}
