package com.example.Tasks.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.Tasks.Model.User;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authManager;

    public void login(User user){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }
    
}
