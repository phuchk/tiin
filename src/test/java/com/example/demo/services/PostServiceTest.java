package com.example.demo.services;

import com.example.demo.exception.CustomException;
import com.example.demo.model.CategoryEntity;
import com.example.demo.model.PostEntity;
import com.example.demo.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock
    PostRepository postRepository;
    @InjectMocks
    PostService postService;

    @Test
    void canFindAllPost() {
//        when
        postService.findAll();
        //then
        verify(postRepository).findAll();
    }

    @Test
    void canAddPost() {
        //given
        PostEntity post = new PostEntity(1, "asd",
                "asdas", "adas", Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now()), new CategoryEntity(1, "car", new ArrayList<>()));
        //when
        postService.addPost(post);
        //then
        verify(postRepository).save(post);
    }

    @Test
    void WillThrowWhenDeletePost() {
        //given
        given(postRepository.existsById(anyLong())).willReturn(false);
        //then
        assertThatThrownBy(() -> postService.deletePost(anyLong()))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("khong tim thay post de xoa");
        verify(postRepository, never()).deleteById(any());

    }

    @Test
    void canDeletePost() {
        //given
        given(postRepository.existsById(anyLong())).willReturn(true);
        //when
        postService.deletePost(anyLong());
        //then
        verify(postRepository).deleteById(any());

    }

    @Test
    void updatePost() {
        //given
        PostEntity post = new PostEntity(1, "asd",
                "asdas", "adas", Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now()), new CategoryEntity(1, "car", new ArrayList<>()));
        //when
        postService.updatePost(post);
        //then
        verify(postRepository).save(post);
    }

    @Test
    void willThrowWhenFindPostById() {
        //then
        assertThatThrownBy(() -> postService.deletePost(anyLong()))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("khong tim thay post");
        verify(postRepository, never()).deleteById(anyLong());

    }
}