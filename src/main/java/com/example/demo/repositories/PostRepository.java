package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
