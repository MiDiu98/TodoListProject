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

    @GetMapping("/{id}")
    Task get(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            return optionalTask.get();
        }

        throw new NotFoundException(String.format("Task id %d not found", id));
    }

    @GetMapping("/find")
    Iterable<Task> findByTitle(@RequestParam String title) {
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

    @PutMapping
    void put(@RequestBody Task task) {
        taskRepository.save(task);
    }
}
