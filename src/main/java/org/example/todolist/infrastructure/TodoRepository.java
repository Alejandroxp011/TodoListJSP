package org.example.todolist.infrastructure;

import org.example.todolist.domain.entity.TodoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoRepository {

    private final List<TodoItem> list;

    public TodoRepository() {
        this.list = new ArrayList<>();
    }

    public List<TodoItem> findAll(){
        return list;
    }

    public void save(TodoItem todoItem){
        list.add(todoItem);
    }

    public void updateById(TodoItem todoItem){
        list.stream()
                .filter(item -> item.getId() == todoItem.getId())
                .findFirst()
                .ifPresent(item -> {
                    item.setTitle(todoItem.getTitle());
                    item.setDescription(todoItem.getDescription());
                    item.setCompleted(todoItem.isCompleted());
                    item.setTargetDate(todoItem.getTargetDate());
                });
    }

    public void deleteById(int id){
        list.removeIf(todoItem -> todoItem.getId() == id);
    }

    public Optional<TodoItem> findById(int id){
        return list.stream()
                .filter(todoItem -> todoItem.getId() == id)
                .findFirst();
    }

}
