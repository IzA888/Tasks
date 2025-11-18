package com.example.Tasks.Services;
import org.springframework.stereotype.Service;

import com.example.Tasks.Model.Tasks;
import com.example.Tasks.Repo.TasksRepository;

import jakarta.transaction.Transactional;

@Service
public class TasksService implements ITasksService {

    private TasksRepository tasksRepository;


    @Override
    public Iterable<Tasks> list() {
        // This method will return a list of tasks
        return tasksRepository.findAll();
    }

    @Override
    @Transactional
    public Tasks save(Tasks tasks) {
        // This method will save a task
        if (tasks != null) {
            return tasksRepository.save(tasks);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    @Transactional
    public Tasks update(Long id, Tasks tasks) {
        if(tasksRepository.findById(id).isPresent()){
            return tasksRepository.save(tasks);
        } else {
            throw new RuntimeException();
        }
    }

}
