package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.PostEntity;
import com.example.demo.services.CategoryService;
import com.example.demo.services.PostService;

@Controller
@RequestMapping({ "/", "/index" })
public class IndexController {
  @Autowired
  PostService postService;
  @Autowired
  CategoryService categoryService;

  @GetMapping("/food")
  public String food(Model model) {
    List<PostEntity> mList = categoryService.findBycategoryName("food");
    model.addAttribute("list", mList);
    return "food";
  }

  @GetMapping("")
  public String main(Model model) {
    List<PostEntity> mList = postService.findAll();
    model.addAttribute("list", mList);
    return "index";
  }

  @GetMapping("travel")
  public String travel(Model model) {
    List<PostEntity> mList = categoryService.findBycategoryName("travel");
    model.addAttribute("list", mList);
    return "travel";
  }

  @GetMapping("car")
  public String car(Model model) {
    List<PostEntity> mList = categoryService.findBycategoryName("car");
    model.addAttribute("list", mList);
    return "car";
  }

  @GetMapping("detail")
  public String detail(@RequestParam(value = "id") String id, Model model) {
    Long postId = Long.parseLong(id);
    PostEntity post = postService.findPostById(postId);
    model.addAttribute("post", post);
    return "detail";
  }
}