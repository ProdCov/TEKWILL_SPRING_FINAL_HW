package com.TEKWILL_STUDY.course.controller;

import com.TEKWILL_STUDY.course.dto.ProjectDTO;
import com.TEKWILL_STUDY.course.dto.TaskDTO;
import com.TEKWILL_STUDY.course.service.ProjectsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    ProjectsService projectsService;

    @GetMapping("/api/projects")
    public ResponseEntity<List<ProjectDTO>> printAllProjects() {
        return projectsService.printAllProjects();
    }

    @PostMapping("/api/projects")
    public ResponseEntity<String> addNewProject(@RequestBody @Valid ProjectDTO projectDTO) {
        projectsService.addNewProject(projectDTO);
        return ResponseEntity.ok("Project added successfully");
    }


    @GetMapping("/api/projects/{projectId}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable int projectId) {
        return projectsService.getProjectById(projectId);
    }

    @PutMapping("/api/projects/{projectId}")
    public ResponseEntity<String> updateProjectById(@PathVariable int projectId, @RequestBody ProjectDTO projectDTO) {
        projectsService.updateProjectById(projectId, projectDTO);
        return ResponseEntity.ok("Project with id " + projectId + "updated successfully");
    }

    @DeleteMapping("/api/projects/{projectId}")
    public ResponseEntity<String> deleteProjectById(@PathVariable int projectId) {
        projectsService.deleteProjectById(projectId);
        return ResponseEntity.ok("Project deleted successfully");
    }

    @GetMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<List<List<TaskDTO>>> getTasks(@PathVariable int projectId) {
        return projectsService.getTasks(projectId);
    }

    @PostMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<String> addTask(@PathVariable int projectId, @RequestBody @Valid TaskDTO taskDTO) {
        return projectsService.addTask(projectId, taskDTO);
    }

    @GetMapping("/api/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<List<TaskDTO>> getTask(@PathVariable int projectId, @PathVariable int taskId) {

        return ResponseEntity.ok(projectsService.getTask(projectId, taskId));
    }

    @PutMapping("/api/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable int projectId, @PathVariable int taskId, @RequestBody @Valid TaskDTO taskDTO) {
        projectsService.updateTask(projectId, taskId, taskDTO);
        return ResponseEntity.ok("Tasks updated successfully");
    }

    @DeleteMapping("/api/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable int projectId, @PathVariable int taskId) {
        return ResponseEntity.ok(projectsService.deleteTask(projectId, taskId));
    }
}
