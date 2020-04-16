package com.ungmydieu.todolist.controllers;

import com.ungmydieu.todolist.models.Task;
import com.ungmydieu.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/todoList")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    Iterable<Task> get() {
        return taskRepository.findAll();
    }

    @GetMapping("/find")
    Iterable<Task> findByTitle(@RequestParam String title) {
        return taskRepository.findByTitleContaining(title);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        taskRepository.deleteById(id);
    }

    @PostMapping
    void post(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @PutMapping
    void put(@RequestBody Task task) {
        taskRepository.save(task);
    }
}
