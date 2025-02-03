package org.example.todolist.application;

import org.example.todolist.domain.entity.TodoItem;
import org.example.todolist.infrastructure.TodoRepository;

import java.util.List;

public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService() {
        this.todoRepository = new TodoRepository();
    }

    public List<TodoItem> getAllTasks() {
        return todoRepository.findAll();
    }

    public void addTask(TodoItem todoItem) {
        todoRepository.save(todoItem);
    }

    public void updateTask(TodoItem todoItem) {
        todoRepository.updateById(todoItem);
    }

    public void changeCompleteTask(int id) {
        todoRepository.updateById(todoRepository.findById(id).map(todoItem -> {
            todoItem.setCompleted(!todoItem.isCompleted());
            return todoItem;
        }).orElseThrow(() -> new RuntimeException("Task not found")));
    }

    public void deleteTask(int id) {
        todoRepository.deleteById(id);
    }

    public TodoItem getTaskDetails(int id) {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }
}
