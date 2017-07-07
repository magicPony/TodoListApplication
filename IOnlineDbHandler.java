package com.example.taras.todolistapplication;

/**
 * Created by Taras on 7/6/2017.
 */

public interface IOnlineDbHandler {
    void addTodo(TodoModel todo);
    void removeTodo(String id);
}
