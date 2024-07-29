package com.todolist.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Todo {
    private int id;
    private int userId;
    private String title;
    private String description;
}
