package com.example.Tasks.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.Tasks.Services.TasksService;
import com.example.Tasks.Model.Tasks;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {

    private TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping(value = {"", "/"})
    public Iterable<Tasks> list() {
        // This method will return a list of tasks
        return this.tasksService.list(); 
    }

    @PostMapping(value = {"/save"})
    public Tasks save(@RequestBody Tasks task) {
        // This method will save a task
        return this.tasksService.save(task);
    }
    
}
