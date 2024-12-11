package com.todo.todoapp.services;

import java.util.List;
import com.todo.todoapp.models.Task;
import com.todo.todoapp.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TaskRepository jpa;
    
    public List<Task> getAllTasks(){
        return jpa.findAll();
    }

    public void createTask(String title){
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);

        jpa.save(task);
    }

    public void deleteTask(Long id){
        jpa.deleteById(id);
    }

    public void toggleTask(Long id){
        Task task = jpa.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Task"));
        
        task.setCompleted(!task.isCompleted());
        jpa.save(task);
    }
}
