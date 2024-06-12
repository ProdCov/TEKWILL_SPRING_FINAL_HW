package com.TEKWILL_STUDY.course.controller;

import com.TEKWILL_STUDY.course.dto.UserDTO;
import com.TEKWILL_STUDY.course.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UsersService usersService;

    @PostMapping("/api/user/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserDTO userDTO) {
        usersService.register(userDTO);
        return ResponseEntity.ok("User registration successfully!");
    }

    @GetMapping("/api/user/{userId}")
    public ResponseEntity<UserDTO> printUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(usersService.printUser(userId));
    }

    @PutMapping("/api/user/{userId}")
    public ResponseEntity<String> update(@RequestBody UserDTO userDTO, @PathVariable int userId) {
        usersService.updateUser(userDTO, userId);
        return ResponseEntity.ok("User with id " + userId + " updated successfully!");
    }

    @PutMapping("/api/user/{userId}/password")
    public ResponseEntity<String> password(@PathVariable Integer userId, @RequestBody Map<String, Object> requestBody) {
        String oldPassword = (String) requestBody.get("oldPassword");
        String newPassword = (String) requestBody.get("newPassword");
        return usersService.updatePassword(userId, oldPassword, newPassword);
    }

    @DeleteMapping("/api/user/{userId}")
    public ResponseEntity<String> delete(@PathVariable int userId) {
        usersService.delete(userId);
        return ResponseEntity.ok("User with id " + userId + " deleted successfully");
    }
}
