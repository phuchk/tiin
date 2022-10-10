package com.example.demo.services;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CustomException;
import com.example.demo.model.PostEntity;
import com.example.demo.repositories.PostRepository;

@Service
@AllArgsConstructor
public class PostService {
    @Autowired
    PostRepository postRepository;

    public List<PostEntity> findAll() {
        return postRepository.findAll();
    }

    public void addPost(PostEntity post) {
        postRepository.save(post);
    }

    public void deletePost(long id) {
        if (postRepository.existsById(id))
            postRepository.deleteById(id);
        else {
            throw new CustomException("khong tim thay post de xoa");
        }
    }

    public void updatePost(PostEntity post) {
        postRepository.save(post);

    }

    public PostEntity findPostById(long id) {
        return postRepository.findById(id).orElseThrow(() -> new CustomException("khong tim thay post"));
    }

}
