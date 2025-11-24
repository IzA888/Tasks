package com.example.Tasks.Controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Tasks.Controller.factory.TasksFactory;
import com.example.Tasks.DTO.TasksDto;
import com.example.Tasks.Services.Interface.ITasksService;



@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private ITasksService tasksService;

    private TasksFactory factory;

    @GetMapping(value = {"", "/"})
    public  ResponseEntity<Iterable<TasksDto>> list() {
        return ResponseEntity.ok().body(factory.toDto(tasksService.list())); 
    }

    @PostMapping(value = {"/save"})
    public ResponseEntity<TasksDto> save(@Validated @RequestBody TasksDto task) {
        return ResponseEntity.ok().body(factory.toDto(tasksService.save(factory.toEntity(task))));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TasksDto> update(@Validated @PathVariable Long id, @RequestBody TasksDto tasks) {
        return  ResponseEntity.ok().body(factory.toDto(tasksService.update(id, factory.toEntity(tasks))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TasksDto>> getById(@RequestParam Long id) {
        return ResponseEntity.ok().body(factory.toDto(tasksService.findById(id)));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@RequestBody Long id) {        
        tasksService.delete(id);
        return ResponseEntity.ok("Apagado");

    }
    
}