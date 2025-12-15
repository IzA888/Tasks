package com.example.Tasks.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Tasks.Model.User;
import com.example.Tasks.Repo.UserRepository;
import com.example.Tasks.Services.Interface.IUserService;

import jakarta.transaction.Transactional;

@Service
public class UserService implements IUserService, UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        
        if(!userRepository.findByUsername(user.getPassword()).isPresent()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
           return userRepository.save(user);
        } else { 
            throw new RuntimeException("Usuário já existe");
        }
    }

    @Override
    @Transactional
    public User updateUser(Long id, User user) {
        if(userRepository.findById(id).isPresent()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
           return userRepository.save(user);
        } else { 
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        if(userRepository.findById(id).isPresent()){
           return userRepository.findById(id);
        } else { 
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if(userRepository.findById(id).isPresent()){
           userRepository.deleteById(id);
        } else { 
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    @Override
    public Optional<User> getByUsername(String username) {
        if(username != null){
           return userRepository.findByUsername(username);
        } else { 
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User getUserLogado(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String nome = null;

        if(principal instanceof UserDetails){
           nome = ((UserDetails) principal).getUsername();
        } else {
            principal.toString();
        }

        return userRepository.findByUsername(nome)
                                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    
}
