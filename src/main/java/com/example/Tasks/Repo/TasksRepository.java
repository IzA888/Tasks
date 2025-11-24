package com.example.Tasks.Repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tasks.Model.Tasks;


public interface TasksRepository extends JpaRepository<Tasks, Long> {
    
    Optional<Tasks> findById(Long id);

    List<Tasks> findAllByUser(String username);
}
