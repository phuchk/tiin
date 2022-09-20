package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.CategoryEntity;


@Repository
public interface CategoryRository extends JpaRepository<CategoryEntity, Long> {
   CategoryEntity findBycategoryName(String name);
}
