package com.TEKWILL_STUDY.course.repository;

import com.TEKWILL_STUDY.course.model.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends CrudRepository<UserModel, Integer> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE users " +
            "SET user_name=:userName, user_country=:userCountry, user_email=:userEmail" +
            " WHERE user_id =:userId", nativeQuery = true)
    void findByUserIdAndUpdate(@Param("userId") int userId, @Param("userName") String userName,
                               @Param("userCountry") String userCountry, @Param("userEmail") String userEmail);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE users " +
            "set user_password=:userPassword" +
            " where user_id=:userId", nativeQuery = true)
    void findByIdAndUpdatePassword(@Param("userId") int userId, @Param("userPassword") String userPassword);

    UserModel findByUserId(int userId);
}
