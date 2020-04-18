package com.ungmydieu.todolist.repositoryTest;

import com.ungmydieu.todolist.models.Task;
import com.ungmydieu.todolist.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void test_findByTitleContaining() {
        Task task = new Task(1,"title","des",null,null);
        taskRepository.save(task);

        assertEquals(taskRepository.findByTitleContaining("ti"), Arrays.asList(task));
        assertEquals(taskRepository.findByTitleContaining("he"), Arrays.asList());
    }
}
