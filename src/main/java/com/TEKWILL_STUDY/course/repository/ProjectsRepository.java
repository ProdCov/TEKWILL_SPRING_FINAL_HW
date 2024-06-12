package com.TEKWILL_STUDY.course.repository;

import com.TEKWILL_STUDY.course.model.ProjectModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


public interface ProjectsRepository extends CrudRepository<ProjectModel, Integer> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE projects " +
            "SET project_name=:projectName, project_description=:projectDescription, date_start=:projectDateStart, date_end=:projectDateEnd" +
            " WHERE project_id=:projectId", nativeQuery = true)
    void findByProjectIdAndUpdate(@Param("projectId") int projectId, @Param("projectName") String projectName, @Param("projectDescription") String projectDescription,
                                  @Param("projectDateStart") Date projectDateStart, @Param("projectDateEnd") Date projectDateEnd);

    ProjectModel findByProjectId(int projectId);

}
