package org.example.todolist.application;

import org.example.todolist.entity.TodoItem;

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

    public void updateTask(int id, String title, String description, boolean completed, LocalDate targetDate) {

    }

    public void changeCompleteTask(int id) {
        todoList.stream()
                .filter(item -> item.getId() == id)
                .forEach(item -> item.setCompleted(!item.isCompleted()));
    }

    public void deleteTask(int id) {
        todoList.removeIf(item -> item.getId() == id);
    }

    public TodoItem getTaskDetails(int id) {
        return todoList.stream().filter(item -> item.getId() == id).toList().getFirst();
    }
}
