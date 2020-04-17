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

    @Before
    public void init() {
        task1 = taskRepository.save(new Task(1,"title", "des", null, null, false));
        task2 = taskRepository.save(new Task(2,"title2","des", null, null, true));
    }

    @After
    public void destroy() {
        taskRepository.deleteAll();
    }

    @Test
    public void test_getAllTask() throws Exception {
        mockMvc.perform(get("/api/todos/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(task1.getId())))
                .andExpect(jsonPath("$[0].title", Matchers.equalTo("title")))
                .andExpect(jsonPath("$[0].description", Matchers.equalTo("des")))
                .andExpect(jsonPath("$[0].startTime", Matchers.equalTo(null)))
                .andExpect(jsonPath("$[0].endTime", Matchers.equalTo(null)))
                .andExpect(jsonPath("$[0].status", Matchers.equalTo(false)))
                .andExpect(jsonPath("$[1].id", Matchers.equalTo(task2.getId())))
                .andExpect(jsonPath("$[1].title", Matchers.equalTo("title2")))
                .andExpect(jsonPath("$[1].description", Matchers.equalTo("des")))
                .andExpect(jsonPath("$[1].startTime", Matchers.equalTo(null)))
                .andExpect(jsonPath("$[1].endTime", Matchers.equalTo(null)))
                .andExpect(jsonPath("$[1].status", Matchers.equalTo(true)));
    }

    @Test
    public void test_getTaskByTitle_Found() throws Exception {
        mockMvc.perform(get("/api/todos/find?title=" + task2.getTitle()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.equalTo(task2.getId())))
                .andExpect(jsonPath("$.title", Matchers.equalTo("title2")))
                .andExpect(jsonPath("$.description", Matchers.equalTo("des")))
                .andExpect(jsonPath("$.startTime", Matchers.equalTo(null)))
                .andExpect(jsonPath("$.endTime", Matchers.equalTo(null)))
                .andExpect(jsonPath("$.status", Matchers.equalTo(true)));
    }

    @Test
    public void test_getTaskByTitle_NotFound() throws Exception {
        mockMvc.perform(get("/api/todos/find?title=" + task2.getTitle()+task1.getTitle()))
                .andDo(print())
                .andExpect(status().isNotFound());
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

    @Test
    public void test_put_Found() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(new Task(task2.getId(), "updateTitle2", "des", null, null, true));

        mockMvc.perform(put("/api/todos")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        Optional<Task> task = taskRepository.findById(task2.getId());

        assertTrue(task.isPresent());
        assertEquals(task.get().getTitle(),"updateTitle2");
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
}
