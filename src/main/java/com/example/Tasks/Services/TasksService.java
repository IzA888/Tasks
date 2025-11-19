package com.example.Tasks.Services;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Tasks.Model.Tasks;
import com.example.Tasks.Repo.TasksRepository;

import jakarta.transaction.Transactional;

@Service
public class TasksService implements ITasksService {

    private TasksRepository tasksRepository;


    @Override
    public List<Tasks> list() {
        if (!tasksRepository.findAll().isEmpty()) {
            return tasksRepository.findAll();            
        } else {
            throw new RuntimeException("Sem tasks salvas");
        }
    }

    @Override
    @Transactional
    public Tasks save(Tasks tasks) {
        if (tasks != null) {
            return tasksRepository.save(tasks);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    @Transactional
    public Tasks update(Long id, Tasks tasks) {
        if (tasksRepository.findById(id).isPresent()) {
            return tasksRepository.save(tasks);
        } else {
            throw new RuntimeException("Task não encontrada");
        }
    }

    @Override
    public Optional<Tasks> findById(Long id) {
        if (tasksRepository.existsById(id)) {
            return tasksRepository.findById(id);
        } else {
            throw new RuntimeException("Tasks não encontrada");
        }
    }

    @Override
    public void delete(Long id) {
        if (tasksRepository.existsById(id)) {
            tasksRepository.deleteById(id);
        } else {
            throw new RuntimeException("Task não encontrada");
        }
    }
    


}
