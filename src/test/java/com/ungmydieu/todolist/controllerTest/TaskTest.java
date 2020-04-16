package com.ungmydieu.todolist.controllerTest;

import com.google.gson.Gson;
import com.ungmydieu.todolist.models.Task;
import com.ungmydieu.todolist.repositories.TaskRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class TaskTest {

    @Autowired
    private TaskRepository taskRepository;

    private Task task1;
    private Task task2;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() {
        task1 = taskRepository.save(new Task(1, "title", "des", null, null, false));
        task2 = taskRepository.save(new Task(2, "title2", "des", null, null, true));
    }

    @After
    public void destroy() {
        taskRepository.deleteAll();
    }

}
