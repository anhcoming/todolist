package com.example.todolist.service.impl;

import com.example.todolist.dto.TaskDto;
import com.example.todolist.entity.TaskEntity;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskEntity> search(String keyword) {
        return taskRepository.searchByTitle(keyword);
    }

    @Override
    public TaskEntity addTask(TaskDto task) {
        if (taskRepository.existsByTitle(task.getTitle())) {
            throw new RuntimeException("error.notfoundId");
        }
        TaskEntity newTask = new TaskEntity();
        BeanUtils.copyProperties(task, newTask);
        return taskRepository.save(newTask);
    }

    @Override
    public TaskEntity updateTask(TaskDto task) {
        TaskEntity taskEntity = taskRepository.findById(task.getId())
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + task.getId()));
        BeanUtils.copyProperties(task, taskEntity);
        return taskRepository.save(taskEntity);
    }


    @Override
    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTask(int id) {
        if(!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }
}
