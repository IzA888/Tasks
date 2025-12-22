package com.example.Tasks.Services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.Tasks.Model.Tasks;
import com.example.Tasks.Repo.TasksRepository;
import com.example.Tasks.Services.Interface.ITasksService;

import jakarta.transaction.Transactional;

@Service
public class TasksService implements ITasksService {

    @Autowired
    private UserService userService;

    @Autowired
    private TasksRepository tasksRepository;

    private String getUsusarioLogado(){   
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")){
            throw new IllegalStateException("Nenhum usuário autenticado");
        } 
        return auth.getName();
    }

    
    @Override
    public List<Tasks> list() {
        String username = getUsusarioLogado();
        if (!tasksRepository.findAllByUserUsername(username).isEmpty()) {
            return tasksRepository.findAllByUserUsername(username);            
        } else {
            throw new RuntimeException("Sem tasks salvas");
        }
    }

    @Override
    @Transactional
    public Tasks save(Tasks tasks) {
        String username = getUsusarioLogado();
        if (tasks != null) {
            tasks.setUser(userService.getByUsername(username).get());
            return tasksRepository.save(tasks);
        } else {
            throw new RuntimeException("Erro ao salvar task");
        }
    }

    @Override
    @Transactional
    public Tasks update(Long id, Tasks tasks) {
        String username = getUsusarioLogado();
        if (tasksRepository.findById(id).isPresent()) {
            tasks.setUser(userService.getByUsername(username).get());
            return tasksRepository.save(tasks);
        } else {
            throw new RuntimeException("Task não encontrada");
        }
    }

    @Override
    public Optional<Tasks> findById(Long id) {
        if (tasksRepository.existsById(id)) {
            return tasksRepository.findById(id);
        } else {
            throw new RuntimeException("Tasks não encontrada");
        }
    }

    @Override
    public void delete(Long id) {
        if (tasksRepository.existsById(id)) {
            tasksRepository.deleteById(id);
        } else {
            throw new RuntimeException("Task não encontrada");
        }
    }
    


}
