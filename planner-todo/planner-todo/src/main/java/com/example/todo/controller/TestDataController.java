package com.example.todo.controller;

import com.example.entity.Category;
import com.example.entity.Priority;
import com.example.entity.Task;
import com.example.todo.service.CategoryService;
import com.example.todo.service.PriorityService;
import com.example.todo.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/data")
public class TestDataController {
    private final TaskService taskService;
    private final PriorityService priorityService;
    private final CategoryService categoryService;

    public TestDataController(TaskService taskService, PriorityService priorityService, CategoryService categoryService) {
        this.taskService = taskService;
        this.priorityService = priorityService;
        this.categoryService = categoryService;
    }

    @PostMapping("/init")
    public ResponseEntity<Boolean> init(@RequestBody Long userId) {

        Priority prior1 = new Priority();
        prior1.setColor("#fff");
        prior1.setTitle("Важный");
        prior1.setUserId(userId);

        Priority prior2 = new Priority();
        prior2.setColor("#ffе");
        prior2.setTitle("Неважный");
        prior2.setUserId(userId);

        priorityService.add(prior1);
        priorityService.add(prior2);


        Category cat1 = new Category();
        cat1.setTitle("Работа");
        cat1.setUserId(userId);

        Category cat2 = new Category();
        cat2.setTitle("Семья");
        cat2.setUserId(userId);

        categoryService.add(cat1);
        categoryService.add(cat2);

        // завтра
        Date tomorrow = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(tomorrow);
        c.add(Calendar.DATE, 1);
        tomorrow = c.getTime();

        // неделя
        Date oneWeek = new Date();
        Calendar c2 = Calendar.getInstance();
        c2.setTime(oneWeek);
        c2.add(Calendar.DATE, 7);
        oneWeek = c2.getTime();

        Task task1 = new Task();
        task1.setTitle("Покушать");
        task1.setCategory(cat1);
        task1.setPriority(prior1);
        task1.setCompleted(true);
        task1.setTaskDate(tomorrow);
        task1.setUserId(userId);

        Task task2 = new Task();
        task2.setTitle("Поспать");
        task2.setCategory(cat2);
        task2.setCompleted(false);
        task2.setPriority(prior2);
        task2.setTaskDate(oneWeek);
        task2.setUserId(userId);


        taskService.add(task1);
        taskService.add(task2);

        return ResponseEntity.ok(true);
    }
}