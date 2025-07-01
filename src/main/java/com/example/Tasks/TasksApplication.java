package com.example.Tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.example.Tasks.Model.Tasks;
import com.example.Tasks.Repo.TasksRepository;
;

@SpringBootApplication
public class TasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(TasksRepository tasksRepository) {
		return args -> {
			// Initial data setup can be done here if needed
			Tasks task1 = new Tasks((long) 1, "Task 1", LocalDate.now(), false);
			Tasks task2 = new Tasks((long) 2, "Task 2", LocalDate.now(), true);
			tasksRepository.save(task1);
			tasksRepository.save(task2);
		};
	}

}
