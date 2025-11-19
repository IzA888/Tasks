package com.example.Tasks.Controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Tasks.Model.Tasks;
import com.example.Tasks.Services.ITasksService;



@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private ITasksService tasksService;

    @GetMapping(value = {"", "/"})
    public Iterable<Tasks> list() {
        return tasksService.list(); 
    }

    @PostMapping(value = {"/save"})
    public Tasks save(@RequestBody Tasks task) {
        // This method will save a task
        return tasksService.save(task);
    }
    
    @PutMapping("/{id}")
    public Tasks update(@PathVariable Long id, @RequestBody Tasks tasks) {
        return tasksService.update(id, tasks);
    }

    @GetMapping("/{id}")
    public Optional<Tasks> getById(@RequestParam Long id) {
        return tasksService.findById(id);
    }
    
    @DeleteMapping("/{id}")
    public void deleteById(@RequestBody Long id) {        
        tasksService.delete(id);
    }
    
}