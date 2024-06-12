package com.TEKWILL_STUDY.course.converter;

import com.TEKWILL_STUDY.course.dto.ProjectDTO;
import com.TEKWILL_STUDY.course.model.ProjectModel;
import com.TEKWILL_STUDY.course.repository.TasksRepository;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;

import java.text.ParseException;
import java.util.Locale;


@UtilityClass
public class ProjectDTOtoModelConverter {
    @Autowired
    TasksRepository tasksRepository;

    public static ProjectDTO convertToDto(ProjectModel model) {

        assert model.getTaskModel() != null;
        return ProjectDTO.builder()
                .projectId(model.getProjectId())
                .projectId(model.getProjectId())
                .projectName(model.getProjectName())
                .projectDescription(model.getProjectDescription())
                .startDate(model.getProjectDateStart().toString())
                .endDate(model.getProjectDateEnd().toString())
                .currentTasks(model.getTaskModel() == null ? null : model.getTaskModel().stream().map(e -> e.getTaskName().toString()).toList())
                .currentTasks(model.getTaskModel().stream().map(e -> e.getTaskId() + "." + e.getTaskName() + ":" + e.getTaskStatus()).toList())
                .build();
    }

    public static ProjectModel convertToModel(ProjectDTO dto) {
        try {
            return ProjectModel.builder()
                    .projectName(dto.getProjectName())
                    .projectDescription(dto.getProjectDescription())
                    .projectDateStart(new DateFormatter("dd/MM/yyyy").parse(dto.getStartDate(), Locale.ENGLISH))
                    .projectDateEnd(new DateFormatter("dd/MM/yyyy").parse(dto.getEndDate(), Locale.ENGLISH))
//                    .taskModel(tasksRepository.findByProjectId(dto.getProjectId()).stream().toList())
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
