package com.TEKWILL_STUDY.course.converter;

import com.TEKWILL_STUDY.course.dto.UserDTO;
import com.TEKWILL_STUDY.course.model.TaskModel;
import com.TEKWILL_STUDY.course.model.UserModel;
import com.TEKWILL_STUDY.course.repository.TasksRepository;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;

@UtilityClass
public class UserDTOtoModelConverter {

    @Autowired
    TasksRepository tasksRepository;

    public static UserDTO convertToDto(UserModel model) {

        assert model.getTaskModel() != null;
        return UserDTO.builder()
                .userId(model.getUserId())
                .userName(model.getUserName())
                .userPassword(model.getUserPassword())
                .userCountry(model.getUserCountry())
                .userEmail(model.getUserEmail())
                .assignedTask(model.getTaskModel() == null ? null : model.getTaskModel().stream().map(TaskModel::getTaskName).toList())
                .build();
    }

    public static UserModel convertToModel(UserDTO dto) {
        return UserModel.builder()
                .userName(dto.getUserName())
                .userPassword(dto.getUserPassword())
                .userCountry(dto.getUserCountry())
                .userEmail(dto.getUserEmail())
                .build();
    }
}
