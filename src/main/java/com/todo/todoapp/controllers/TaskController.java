package com.todo.todoapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.todo.todoapp.models.Task;
import com.todo.todoapp.services.TodoService;

@Controller
@RequestMapping("/app")
public class TaskController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/getTasks")
    public String getTasks(Model model){
        List<Task> tasks = todoService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/createTask")
    public String createTask(@RequestParam String title){
        todoService.createTask(title);
        return "redirect:/app/getTasks";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id){
        todoService.deleteTask(id);
        return "redirect:/app/getTasks";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id){
        todoService.toggleTask(id);
        return "redirect:/app/getTasks";
    }
}   
