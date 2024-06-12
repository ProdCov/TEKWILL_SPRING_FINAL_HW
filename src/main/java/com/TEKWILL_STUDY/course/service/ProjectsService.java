package com.TEKWILL_STUDY.course.service;

import com.TEKWILL_STUDY.course.converter.ProjectDTOtoModelConverter;
import com.TEKWILL_STUDY.course.converter.TaskDTOtoModelConverter;
import com.TEKWILL_STUDY.course.dto.ProjectDTO;
import com.TEKWILL_STUDY.course.dto.TaskDTO;
import com.TEKWILL_STUDY.course.model.ProjectModel;
import com.TEKWILL_STUDY.course.model.TaskModel;
import com.TEKWILL_STUDY.course.repository.ProjectsRepository;
import com.TEKWILL_STUDY.course.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class ProjectsService {

    @Autowired
    ProjectsRepository projectsRepository;

    @Autowired
    TasksRepository tasksRepository;


    public void addNewProject(ProjectDTO projectDTO) {
        ProjectModel projectModel = ProjectDTOtoModelConverter.convertToModel(projectDTO);
        projectsRepository.save(projectModel);
    }

    public ResponseEntity<List<ProjectDTO>> printAllProjects() {
        return ResponseEntity.ok(((List<ProjectModel>) projectsRepository.findAll()).stream()
                .map(ProjectDTOtoModelConverter::convertToDto)
                .toList());
    }


    public ResponseEntity<ProjectDTO> getProjectById(Integer projectId) {
        return ResponseEntity.ok(ProjectDTOtoModelConverter.convertToDto(projectsRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))));
    }


    public void updateProjectById(int projectId, ProjectDTO projectDTO) {
        ProjectModel projectModel = ProjectDTOtoModelConverter.convertToModel(projectDTO);
        projectsRepository.findByProjectIdAndUpdate(projectId, projectModel.getProjectName(), projectModel.getProjectDescription(), projectModel.getProjectDateStart(), projectModel.getProjectDateEnd());

//        projectsRepository.findById(projectId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        ProjectModel modelToUpdate = ProjectDTOtoModelConverter.convertToModel(projectDTO);
//        modelToUpdate.setProjectId(projectId);
//        projectsRepository.save(modelToUpdate);
    }


    public void deleteProjectById(int projectId) {
        projectsRepository.deleteById(projectId);
    }


    public ResponseEntity<List<List<TaskDTO>>> getTasks(int projectId) {
        return ResponseEntity.ok((projectsRepository.findById(projectId)).stream().map(e -> {
            assert e.getTaskModel() != null;
            return e.getTaskModel().stream().map(TaskDTOtoModelConverter::convertToDto).toList();
        }).toList());
    }

    public ResponseEntity<String> addTask(int projectId, TaskDTO taskDTO) {
        if (projectsRepository.existsById(projectId)) {
            TaskModel taskModel = TaskDTOtoModelConverter.convertToModel(taskDTO);
            taskModel.setProjectModel(projectsRepository.findByProjectId(projectId));
            tasksRepository.save(taskModel);
            return ResponseEntity.ok("Tasks added successfully");
        }
        return ResponseEntity.ok("Project with " + projectId + "id doesn't exist");
    }


    public List<TaskDTO> getTask(int projectId, int taskId) {
        if (projectsRepository.findByProjectId(projectId).getTaskModel().stream().anyMatch(e -> e.getTaskId() == taskId)) {
            return tasksRepository.findById(taskId).stream()
                    .map(TaskDTOtoModelConverter::convertToDto)
                    .toList();
        }
        return null;
    }


    public ResponseEntity<String> updateTask(int projectId, int taskId, TaskDTO taskDTO) {
        TaskModel modelToUpdate = TaskDTOtoModelConverter.convertToModel(taskDTO);
        if (projectsRepository.findByProjectId(projectId).getTaskModel().stream().anyMatch(e -> e.getTaskId() == taskId)) {
            modelToUpdate.setTaskId(taskId);
            tasksRepository.save(modelToUpdate);
            return ResponseEntity.ok("Task updated successfully");
        }
        return ResponseEntity.ok("Task don't found");
    }

    public String deleteTask(int projectId, int taskId) {
        if (Objects.requireNonNull(projectsRepository.findByProjectId(projectId).getTaskModel()).stream().anyMatch(e -> e.getTaskId() == taskId)) {
            tasksRepository.deleteById(taskId);
            return "Tasks deleted successfully";
        }
        return "In this project doesn't exist " + taskId + " task";
    }


}
