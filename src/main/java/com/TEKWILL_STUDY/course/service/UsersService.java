package com.TEKWILL_STUDY.course.service;

import com.TEKWILL_STUDY.course.converter.UserDTOtoModelConverter;
import com.TEKWILL_STUDY.course.dto.UserDTO;
import com.TEKWILL_STUDY.course.model.UserModel;
import com.TEKWILL_STUDY.course.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public void register(UserDTO userDTO) {
        UserModel userModel = UserDTOtoModelConverter.convertToModel(userDTO);
        usersRepository.save(userModel);
    }

    public UserDTO printUser(Integer userId) {

        return usersRepository.findById(userId).map(UserDTOtoModelConverter::convertToDto).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void updateUser(UserDTO userDTO, int userId) {
        UserModel userModel = UserDTOtoModelConverter.convertToModel(userDTO);
        if (userModel.getUserPassword().equals(usersRepository.findByUserId(userId).getUserPassword())) {
            usersRepository.findByUserIdAndUpdate(userId, userModel.getUserName(), userModel.getUserCountry(), userModel.getUserEmail());
        }

//        usersRepository.findById(userId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        UserModel modelToUpdate = UserDTOtoModelConverter.convertToModel(userDTO);
//
//        modelToUpdate.setUserId(userId);
//
//        usersRepository.save(modelToUpdate);

    }

    public ResponseEntity<String> updatePassword(int userId, String oldPassword, String newPassword) {
        if (usersRepository.existsById(userId)) {
            if (usersRepository.findByUserId(userId).getUserPassword().equals(oldPassword)) {
                usersRepository.findByIdAndUpdatePassword(userId, newPassword);
                return ResponseEntity.ok("Password updated successfully");
            } else return ResponseEntity.ok("Wrong old password, try one more time");
        }
        return ResponseEntity.ok("This user doesn't exist");
    }

    public void delete(int userId) {
        usersRepository.deleteById(userId);
    }
}
