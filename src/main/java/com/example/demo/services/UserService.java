package com.example.demo.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.CustomException;
import com.example.demo.model.UserEntity;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(UserDto user) {
        Boolean existsUser = isUserValid(user.getUsername());
        if (existsUser) {
            throw new CustomException("User name already exists");
        }
        UserEntity userEntity = new UserEntity(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
                "ADMIN");
        userRepository.save(userEntity);
    }

    public Boolean isUserValid(String name) {
        return userRepository.isUserValid(name);
    }

}
