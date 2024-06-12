package com.TEKWILL_STUDY.course.converter;

import com.TEKWILL_STUDY.course.dto.TaskDTO;
import com.TEKWILL_STUDY.course.model.TaskModel;
import com.TEKWILL_STUDY.course.repository.ProjectsRepository;
import com.TEKWILL_STUDY.course.repository.UsersRepository;
import com.TEKWILL_STUDY.course.support.TaskStatus;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;

import java.text.ParseException;
import java.util.Locale;

@UtilityClass
public class TaskDTOtoModelConverter {

    @Autowired
    ProjectsRepository projectsRepository;

    @Autowired
    UsersRepository usersRepository;


    public static TaskDTO convertToDto(TaskModel model) {

        return TaskDTO.builder()
                .taskId(model.getTaskId())
                .taskName(model.getTaskName())
                .taskDescription(model.getTaskDescription())
                .taskDeadline(model.getTaskDeadline().toString())
                .taskStatus(TaskStatus.fromValue(model.getTaskStatus()))
                .userId((model.getUserModel() == null ? null : model.getUserModel().getUserId()))
//                .userId(model.getUserModel().getUserId())
                .projectId(model.getProjectModel() == null ? null : model.getProjectModel().getProjectId())
//                .projectId(model.getProjectModel().getProjectId())
                .build();
    }

    public static TaskModel convertToModel(TaskDTO dto) {
        try {
            return TaskModel.builder()
                    .taskName(dto.getTaskName())
                    .taskDescription(dto.getTaskDescription())
                    .taskDeadline(new DateFormatter("dd/MM/yyyy").parse(dto.getTaskDeadline(), Locale.ENGLISH))
                    .taskStatus(dto.getTaskStatus().getTaskStatus())
//                    .userModel(usersRepository.findById(dto.getUserId() != null ? dto.getUserId() : null)
//                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
//                    .projectModel(projectsRepository.findById(dto.getProjectId() != null ? dto.getProjectId() : null)
//                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
