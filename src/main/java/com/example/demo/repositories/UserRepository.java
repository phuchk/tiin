package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findFirstByUsername(String name);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM user " +
            "WHERE username = ?1",nativeQuery = true
    )
    Boolean isUserValid(String name);

}
