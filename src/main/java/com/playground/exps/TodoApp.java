package com.playground.exps;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class TodoApp {
    public static void main(String[] args) {
        SpringApplication.run(TodoApp.class, args);
    }
}

// Model class
class ToDoItem {
    final private Long id;
    private String task;
    private boolean completed;

    public ToDoItem(Long id, String task) {
        this.id = id;
        this.task = task;
        this.completed = false;
    }

    public Long getId() { return id; }
    public String getTask() { return task; }
    public boolean isCompleted() { return completed; }

    public void setTask(String task) { this.task = task; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}

// Controller class
@RestController
@RequestMapping("/todos")
class ToDoController {
    private final List<ToDoItem> todoList = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    @PostMapping
    public ToDoItem addToDo(@RequestBody ToDoItem item) {
        ToDoItem newItem = new ToDoItem(counter.getAndIncrement(), item.getTask());
        todoList.add(newItem);
        return newItem;
    }

    @GetMapping
    public List<ToDoItem> getAllToDos() {
        return todoList;
    }

    @GetMapping("/{id}")
    public ToDoItem getToDoById(@PathVariable Long id) {
        return todoList.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    public ToDoItem updateToDo(@PathVariable Long id, @RequestBody ToDoItem updatedItem) {
        for (ToDoItem item : todoList) {
            if (item.getId().equals(id)) {
                item.setTask(updatedItem.getTask());
                item.setCompleted(updatedItem.isCompleted());
                return item;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteToDo(@PathVariable Long id) {
        if (todoList.removeIf(item -> item.getId().equals(id))) {
            return "Deleted successfully";
        }
        return "ToDo not found";
    }
}












