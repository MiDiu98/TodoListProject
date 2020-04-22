package com.ungmydieu.todolist.repositories;

import com.ungmydieu.todolist.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Iterable<Task> findByTitleContaining(String pattern);
}
