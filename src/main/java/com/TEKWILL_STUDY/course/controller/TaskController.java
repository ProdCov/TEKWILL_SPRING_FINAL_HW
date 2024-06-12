package com.TEKWILL_STUDY.course.controller;

import com.TEKWILL_STUDY.course.dto.TaskDTO;
import com.TEKWILL_STUDY.course.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TasksService tasksService;

    @GetMapping("/api/tasks/assigned/{userId}")
    public ResponseEntity<List<TaskDTO>> getAssignedTasks(@PathVariable int userId) {
        return ResponseEntity.ok(tasksService.getAssignedTasks(userId));
    }

    @PutMapping("/api/tasks/{taskId}/assign/{userId}")
    public ResponseEntity<String> assignTask(@PathVariable int taskId, @PathVariable int userId) {
        tasksService.assignTask(taskId, userId);
        return ResponseEntity.ok("Task assigned to user " + userId + " with task id " + taskId);
    }

    @PutMapping("/api/tasks/{taskId}/complete")
    public ResponseEntity<String> completeTask(@PathVariable int taskId) {
        tasksService.completeTask(taskId);
        return ResponseEntity.ok("Task with id " + taskId + " is completed!");
    }
}
