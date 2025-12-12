package com.example.Tasks.Controller.factory;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.Tasks.DTO.TasksDto;
import com.example.Tasks.Model.Tasks;

@Component
public class TasksFactory {

    public TasksDto toDto(Tasks tasks) {
        TasksDto dto = new TasksDto();

        dto.setName(tasks.getName());
        dto.setDate(tasks.getData());
        dto.setCompleted(tasks.isCompleted());

        return dto;
    }

    public Optional<TasksDto> toDto(Optional<Tasks> tasks) {
        Optional<TasksDto> dto = Optional.of(new TasksDto());

        dto.get().setName(tasks.get().getName());
        dto.get().setDate(tasks.get().getData());
        dto.get().setCompleted(tasks.get().isCompleted());

        return dto;
    }
    
    public Iterable<TasksDto> toDto(List<Tasks> tasks) {
        return tasks.stream()
                .map(task -> new TasksDto(task.getName(), task.getData(), task.isCompleted()))
                .toList();
    }
    
    public Tasks toEntity(TasksDto dto) {
        Tasks entity = new Tasks();
    
        entity.setName(dto.getName());
        entity.setData(dto.getDate());
        entity.setCompleted(dto.getCompleted());

        return entity;
    }
}