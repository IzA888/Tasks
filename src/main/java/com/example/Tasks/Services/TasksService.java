package com.example.Tasks.Services;
import com.example.Tasks.Model.Tasks;

public interface TasksService {

    Iterable<Tasks> list();
    
    Tasks save(Tasks task);
    
}
