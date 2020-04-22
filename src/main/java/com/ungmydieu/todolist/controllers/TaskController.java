package com.ungmydieu.todolist.controllers;

import com.ungmydieu.todolist.exceptions.NotFoundException;
import com.ungmydieu.todolist.models.Task;
import com.ungmydieu.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tasks")
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
        verifyTaskIdExist(id);
        taskRepository.deleteById(id);
    }
    @PostMapping
    void post(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @PutMapping("/{id}")
    void put(@PathVariable int id, @RequestBody Task task) {
        verifyTaskIdExist(id);
        task.setId(id);
        taskRepository.save(task);
    }

    void verifyTaskIdExist(int id) {
        if (!taskRepository.existsById(id)) {
            throw new NotFoundException(String.format("Task id %d is not found", id));
        }
    }
}
