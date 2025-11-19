package com.example.todolist.controller;

import com.example.todolist.dto.ResponseDto;
import com.example.todolist.dto.TaskDto;
import com.example.todolist.entity.TaskEntity;
import com.example.todolist.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("get-all")
    public List<TaskEntity> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("search")
    public List<TaskEntity> search(@RequestParam(name="keyword") String keyword) {
        return taskService.search(keyword);
    }

    @PostMapping("add")
    public ResponseEntity<TaskEntity> addTask(@RequestBody TaskDto task) {
        TaskEntity savedTask = taskService.addTask(task);
        return ResponseEntity.ok(savedTask);
    }

    @PostMapping("add2")
    public ResponseEntity<ResponseDto<TaskEntity>> addTask2(@RequestBody TaskDto task) {
        TaskEntity savedTask = taskService.addTask(task);
        ResponseDto<TaskEntity> response = new ResponseDto<>(HttpStatus.OK, "Thêm mới thành công", savedTask);
        return ResponseEntity.ok(response);
    }


    @PutMapping("update")
    public ResponseEntity<TaskEntity> updateTask(@RequestBody TaskDto task) {
        TaskEntity savedTask = taskService.updateTask(task);
        return ResponseEntity.ok(savedTask);
    }

    @PostMapping("change-status")
    public ResponseEntity<TaskEntity> changeStatusTask(@RequestBody TaskDto task) {
        TaskEntity savedTask = taskService.updateTask(task);
        return ResponseEntity.ok(savedTask);
    }

    @DeleteMapping("delete/{id}")
    public ResponseDto<TaskEntity> deleteTask(@PathVariable int id) {
        try {
            taskService.deleteTask(id);
            return new ResponseDto<>(HttpStatus.OK, "Delete success", null);
        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
}
