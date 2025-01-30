package org.example.todolist.service;

import org.example.todolist.model.TodoItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoService {
    private final List<TodoItem> todoList = new ArrayList<>();
    private int nextId = 1;

    public List<TodoItem> getAllTasks() {
        return todoList;
    }

    public void addTask(String title, String description, boolean completed, LocalDate targetDate) {
        todoList.add(new TodoItem(nextId++, title, description, completed, targetDate));
    }

    public void changeCompleteTask(int id) {
        todoList.stream()
                .filter(item -> item.getId() == id)
                .forEach(item -> item.setCompleted(!item.isCompleted()));
    }

    public void deleteTask(int id) {
        todoList.removeIf(item -> item.getId() == id);
    }
}
