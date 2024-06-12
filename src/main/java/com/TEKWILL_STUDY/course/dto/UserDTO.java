package com.TEKWILL_STUDY.course.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserDTO {

    private Integer userId;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String userPassword;
    @NotEmpty
    private String userCountry;
    @NotEmpty
    private String userEmail;
    @Nullable
    private List<String> assignedTask;

}
