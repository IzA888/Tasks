package com.example.Tasks.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Tasks.Model.User;
import com.example.Tasks.Repo.UserRepository;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public String login(String username, String password) throws Exception{
        try{
            User user = userRepository.findByUsername(username).orElseThrow(() -> 
                    new UsernameNotFoundException("Usuário não encontrado"));
            if(!user.getUsername().isBlank()){
                Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                return jwtService.generateToken((UserDetails) auth.getPrincipal());
            } else {
                throw new Exception("Error ");
            }
        } catch(BadCredentialsException e) {
            throw new BadCredentialsException("Senha inválida");
        }
    }
    
}
