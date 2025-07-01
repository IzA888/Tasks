package com.example.Tasks.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor


public class Tasks {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dueDate;
    private boolean completed;
   
    public Tasks() {
        //TODO Auto-generated constructor stub
    }
}
