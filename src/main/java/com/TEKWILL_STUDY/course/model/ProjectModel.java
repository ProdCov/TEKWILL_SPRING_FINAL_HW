package com.TEKWILL_STUDY.course.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProjectModel {

    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    @DateTimeFormat(pattern = "yyyy/MM/dd", fallbackPatterns = "dd/MM/yyyy")
    @Column(name = "date_start")
    private Date projectDateStart;

    @DateTimeFormat(pattern = "yyyy/MM/dd", fallbackPatterns = "dd/MM/yyyy")
    @Column(name = "date_end")
    private Date projectDateEnd;

    @Nullable
    @OneToMany(mappedBy = "projectModel", cascade = CascadeType.DETACH, orphanRemoval = true)
    private List<TaskModel> taskModel;
}
