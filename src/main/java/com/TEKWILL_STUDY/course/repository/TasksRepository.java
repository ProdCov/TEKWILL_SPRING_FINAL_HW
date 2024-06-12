package com.TEKWILL_STUDY.course.repository;

import com.TEKWILL_STUDY.course.model.TaskModel;
import com.TEKWILL_STUDY.course.support.TaskStatus;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TasksRepository extends CrudRepository<TaskModel, Integer> {

//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query(value = "UPDATE tasks t " +
//            "SET task_name=:taskName, task_description=:taskDescription, deadline = :taskDeadline, status=:taskStatus " +
//            "WHERE task_id=:taskId", nativeQuery = true)
//    void findByTaskIdAndUpdate(@Param("taskId") int taskId, @Param("taskName") String taskName,
//                               @Param("taskDescription") String taskDescription, @Param("taskDeadline") Date taskDeadline, @Param("taskStatus") TaskStatus taskStatus);
//

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "select * from tasks where fk_user_id = :userId", nativeQuery = true)
    List<TaskModel> findByUserId(@PathVariable Integer userId);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update TaskModel tm set tm.taskStatus = :taskStatus where tm.taskId = :taskId")
    void setTasktoComplete(int taskId, TaskStatus taskStatus);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update TaskModel tm set tm.userModel.userId = :userId where tm.taskId = :taskId")
    void setTaskToUser(int taskId, int userId);
}
