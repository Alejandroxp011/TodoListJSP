package org.example.todolist.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
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
