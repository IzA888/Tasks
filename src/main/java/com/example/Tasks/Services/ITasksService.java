package com.example.Tasks.Services;

import com.example.Tasks.Model.Tasks;

public interface ITasksService {

    Iterable<Tasks> list();
    
    Tasks save(Tasks task);
    
    Tasks update(Long id, Tasks tasks);
}
