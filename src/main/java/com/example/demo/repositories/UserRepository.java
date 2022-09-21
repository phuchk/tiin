package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findFirstByUsername(String name);

}
