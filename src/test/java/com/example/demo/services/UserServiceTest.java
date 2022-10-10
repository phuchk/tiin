package com.example.demo.services;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.CustomException;
import com.example.demo.model.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void canSaveUser() {
        //given
        UserDto userDto = new UserDto("an", "qwe11", "qwe11");
        UserEntity user = new UserEntity
                ("an", bCryptPasswordEncoder.encode("qwe11"), "ADMIN");
        //when
        userService.save(userDto);
        //then
        verify(userRepository).save(user);

    }

    @Test
    void willThrowWhenUsernameExists() {
        //given
        UserDto userDto = new UserDto("an", "qwe11", "qwe11");
        given(userService.isUserValid("an")).willReturn(true);
        //when
        //then
        assertThatThrownBy(() -> userService.save(userDto)).isInstanceOf(CustomException.class)
                .hasMessageContaining("User name already exists");
        verify(userRepository,never()).save(any());
    }

}