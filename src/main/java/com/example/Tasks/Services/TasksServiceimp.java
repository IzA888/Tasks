package com.example.Tasks.Services;
import com.example.Tasks.Repo.TasksRepository;
import com.example.Tasks.Model.Tasks;

public class TasksServiceimp implements TasksService {

    private TasksRepository tasksRepository;

    public TasksServiceimp(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    public Iterable<Tasks> list() {
        // This method will return a list of tasks
        return this.tasksRepository.findAll();
    }

    @Override
    public Tasks save(Tasks task) {
        // This method will save a task
        return this.tasksRepository.save(task);
    }
}
