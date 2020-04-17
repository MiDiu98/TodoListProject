package com.ungmydieu.todolist.controllerTest;

import com.google.gson.Gson;
import com.ungmydieu.todolist.models.Task;
import com.ungmydieu.todolist.repositories.TaskRepository;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class TaskControllerTest {

    @Autowired
    private TaskRepository taskRepository;

    private Task task1;
    private Task task2;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        task1 = taskRepository.save(new Task(1,"title", "des", null, null, false));
        task2 = taskRepository.save(new Task(2,"title2","des", null, null, true));
    }

    @AfterEach
    public void destroy() {
        taskRepository.deleteAll();
    }

    @Test
    public void test_getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/todos")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    public void test_getTaskByTitle_Found() throws Exception {
        mockMvc.perform(get("/api/todos/find")
                        .param("title", "e2")
                ).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value("title2"));
    }

    @Test
    public void test_put_Found() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(new Task(task2.getId(), "updateTitle2", "des", null, null, true));

        mockMvc.perform(put("/api/todos/{id}",task2.getId())
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        Optional<Task> task = taskRepository.findById(task2.getId());

        assertTrue(task.isPresent());
        assertEquals(task.get().getTitle(),"updateTitle2");
    }

    @Test
    public void test_put_NotFound() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(new Task());

        mockMvc.perform(put("/api/todos/{id}", 200)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_post_ok() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(new Task(0, "postTitle", "des", null, null, true));

        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        ArrayList<Task> tasks = (ArrayList<Task>) taskRepository.findAll();
        Task task = tasks.get(tasks.size() - 1);

        assertEquals(task.getTitle(),"postTitle");
    }

    @Test
    public void test_deleteTask_Found() throws Exception {
        mockMvc.perform(delete("/api/todos/" + task1.getId()))
                .andExpect(status().isOk());
        assertFalse(taskRepository.findById(task1.getId()).isPresent());
    }

    @Test
    public void test_deleteTask_NotFound() throws Exception {
        mockMvc.perform(delete("/api/todos/" + task1.getId() + task2.getId()))
                .andExpect(status().isNotFound());
    }
}
