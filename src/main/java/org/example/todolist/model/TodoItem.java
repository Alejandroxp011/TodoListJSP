package org.example.todolist.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoItem {
    private int id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDate targetDate;

    public TodoItem( int id,String title, String description, boolean completed, LocalDate targetDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.targetDate = targetDate;
    }
}
