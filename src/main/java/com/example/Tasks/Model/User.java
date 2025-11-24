package com.example.Tasks.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", unique=true)
    private String username;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy="user")
    private List<Tasks> tasks;

    
    public User(Long id, String password, String username) {
        this.id = id;
        this.password = password;
        this.username = username;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Tasks> getTasks() {
        return tasks;
    }
    
    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }
    
}
