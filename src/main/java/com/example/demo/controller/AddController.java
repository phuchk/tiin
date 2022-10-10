package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.CategoryEntity;
import com.example.demo.model.PostEntity;
import com.example.demo.services.CategoryService;
import com.example.demo.services.PostService;

@Controller
public class AddController {
    @Autowired
    PostService postService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("add")
    public String main(Model model) {
        model.addAttribute("post", new PostEntity());
        model.addAttribute("test", new CategoryEntity());
        List<CategoryEntity> mList = categoryService.getAllCategory();
        model.addAttribute("category", mList);
        return "add";
    }

    @PostMapping("add")
    public String save(@ModelAttribute(value = "post") PostEntity post,
            @ModelAttribute(value = "test") CategoryEntity category) {
        post.setCategoryEntity(category);
        post.setDateCreate(Date.valueOf(LocalDate.now()));
        post.setDateModified(Date.valueOf(LocalDate.now()));
        postService.addPost(post);
        return "redirect:/index";
    }
}