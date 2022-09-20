package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CategoryEntity;
import com.example.demo.model.PostEntity;
import com.example.demo.repositories.CategoryRository;

@Service
public class CategoryService {
    @Autowired
    CategoryRository categoryRository;

    public List<CategoryEntity> getAllCategory() {
        return categoryRository.findAll();
    }

    public List<PostEntity> findBycategoryName(String categoryName) {
        CategoryEntity categoryEntity = categoryRository.findBycategoryName(categoryName);
        return categoryEntity.getMList();
    }

}
