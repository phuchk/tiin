package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.CategoryEntity;
import com.example.demo.model.PostEntity;
import com.example.demo.services.CategoryService;
import com.example.demo.services.PostService;

@Controller
@RequestMapping({ "/admin" })
public class AdminController {
    @Autowired
    PostService postService;
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String main(Model model) {
        List<PostEntity> mList = postService.findAll();
        model.addAttribute("list", mList);
        return "admin";
    }

    @PostMapping
    public String save(PostEntity post, Model model) {
        model.addAttribute("post", post);
        return "saved";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        PostEntity post = postService.findPostById(Long.parseLong(id));
        model.addAttribute("post", post);
        model.addAttribute("test", new CategoryEntity());
        List<CategoryEntity> mList = categoryService.getAllCategory();
        model.addAttribute("category", mList);
        return "edit";
    }

    // loi
    @PostMapping("edit/{id}")
    public String edit(@PathVariable("id") String id, @ModelAttribute("post") PostEntity post) {
        Long categoryId = post.getId();
        Long postId = Long.parseLong(id);
        CategoryEntity category = new CategoryEntity();
        category.setId(categoryId);
        post.setCategoryEntity(category);
        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") String id) {
        postService.deletePost(Long.parseLong(id));
        return "redirect:/admin";
    }

}