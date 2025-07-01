package com.example.Tasks.Repo;
import com.example.Tasks.Model.Tasks;
import org.springframework.data.repository.CrudRepository;

public interface TasksRepository extends CrudRepository<Tasks, Long> {
    // Additional query methods can be defined here if needed

}
