package com.ungmydieu.todolist.controllers;

import com.ungmydieu.todolist.exceptions.NotFoundException;
import com.ungmydieu.todolist.models.Task;
import com.ungmydieu.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/todos")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    Iterable<Task> get() {
        return taskRepository.findAll();
    }

    @GetMapping("/find")
    Iterable<Task> findByTitle(@RequestParam( value="title",required = false) String title) {
        return taskRepository.findByTitleContaining(title);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        if (!taskRepository.existsById(id)) {
            throw new NotFoundException(String.format("Task id %d not found", id));
        }

        taskRepository.deleteById(id);
    }
    @PostMapping
    void post(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @PutMapping("/{id}")
    void put(@PathVariable int id, @RequestBody Task task) {
        if (!taskRepository.existsById(id)) {
            throw new NotFoundException(String.format("Task id %d not found", id));
        }
        task.setId(id);
        taskRepository.save(task);
    }
}
