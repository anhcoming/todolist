package com.example.todolist.service;

import com.example.todolist.dto.TaskDto;
import com.example.todolist.entity.TaskEntity;

import java.util.List;

public interface TaskService {
    TaskEntity addTask(TaskDto taskDto);
    TaskEntity updateTask(TaskDto taskDto);

    List<TaskEntity> getAllTasks();
    List<TaskEntity> search(String keyword);
    void deleteTask(int id);
}
