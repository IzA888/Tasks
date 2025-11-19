package com.example.Tasks.DTO;

import java.time.LocalDate;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TasksDto {

    @NotNull(value = "Titulo não pode ser vazio")
    private String name;
    private LocalDate date;
    private Boolean completed = false;
}
