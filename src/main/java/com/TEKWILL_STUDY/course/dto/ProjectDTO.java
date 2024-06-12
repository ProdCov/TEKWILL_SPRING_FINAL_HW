package com.TEKWILL_STUDY.course.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ProjectDTO {


//    @NotNull
    private Integer projectId;
    @NotEmpty
    private String projectName;
    @NotEmpty
    private String projectDescription;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy", fallbackPatterns = "yyyy/MM/dd")
    private String startDate;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy", fallbackPatterns = "yyyy/MM/dd")
    private String endDate;
    @Nullable
    private List<String> currentTasks;

}
