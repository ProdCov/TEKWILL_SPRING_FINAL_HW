package com.TEKWILL_STUDY.course.dto;

import com.TEKWILL_STUDY.course.support.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@Builder
public class TaskDTO {


    //    @NotNull
    private Integer taskId;
    @NotEmpty
    private String taskName;
    @NotEmpty
    private String taskDescription;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy", fallbackPatterns = "yyyy/MM/dd")
    private String taskDeadline;
    //    @NotEmpty
    @Enumerated(EnumType.STRING)
    @Value("new")
    private TaskStatus taskStatus;
    @Nullable
    private Integer userId;
    @Nullable
    private Integer projectId;
}
