package com.example.Tasks.Services;

import java.util.Optional;

import com.example.Tasks.Model.Tasks;

public interface ITasksService {

    Iterable<Tasks> list();
    
    Tasks save(Tasks task);
    
    Tasks update(Long id, Tasks tasks);

    Optional<Tasks> findById(Long id);

    void delete(Long id);
}
