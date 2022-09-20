package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PostEntity;
import com.example.demo.repositories.PostRepository;

@Service
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
        postRepository.deleteById(id);
    }

    public void updatePost(PostEntity post) {
        postRepository.save(post);

    }

    public PostEntity findPostById(long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("khong tim thay post"));
    }

}
