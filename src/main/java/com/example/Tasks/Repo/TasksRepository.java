package com.example.Tasks.Repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tasks.Model.Tasks;


public interface TasksRepository extends JpaRepository<Tasks, Long> {
    

}
