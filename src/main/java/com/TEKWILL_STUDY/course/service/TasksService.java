package com.TEKWILL_STUDY.course.service;

import com.TEKWILL_STUDY.course.converter.TaskDTOtoModelConverter;
import com.TEKWILL_STUDY.course.dto.TaskDTO;
import com.TEKWILL_STUDY.course.repository.TasksRepository;
import com.TEKWILL_STUDY.course.repository.UsersRepository;
import com.TEKWILL_STUDY.course.support.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class TasksService {

    @Autowired
    TasksRepository tasksRepository;

    @Autowired
    UsersRepository usersRepository;


    public List<TaskDTO> getAssignedTasks(@PathVariable Integer userId) {
        return (tasksRepository.findByUserId(userId).stream().map(TaskDTOtoModelConverter::convertToDto).toList());
    }

    public void assignTask(@PathVariable int taskId, @PathVariable int userId) {

        tasksRepository.setTaskToUser(taskId, userId);
    }

    public void completeTask(@PathVariable int taskId) {
        tasksRepository.setTasktoComplete(taskId, TaskStatus.COMPLETED);
    }
}
