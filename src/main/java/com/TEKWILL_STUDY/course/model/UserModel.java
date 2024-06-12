package com.TEKWILL_STUDY.course.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserModel {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_country")
    private String userCountry;

    @Column(name = "user_email")
    private String userEmail;

    @Nullable
    @OneToMany(mappedBy = "userModel", cascade = CascadeType.DETACH, orphanRemoval = true)
    private List<TaskModel> taskModel;


}
