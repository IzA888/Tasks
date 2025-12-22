package com.example.Tasks.DTO;

import org.jetbrains.annotations.NotNull;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotNull(value = "Username não pode estar vazio")
    private String username;

    @NotNull(value="Senha não pode estar vazio")
    @Size(min=4, message="Deve ter no mínimo 4 caracteres")
    private String password;
}
