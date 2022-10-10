package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.CustomException;
import com.example.demo.model.PostEntity;
import com.example.demo.services.CategoryService;
import com.example.demo.services.PostService;
import com.example.demo.services.UserService;

@Controller
@RequestMapping({ "/", "/index" })
public class IndexController {
  @Autowired
  PostService postService;
  @Autowired
  CategoryService categoryService;
  @Autowired
  UserService userService;

  @GetMapping("/food")
  public String food(Model model) {
    List<PostEntity> mList = categoryService.findBycategoryName("food");
    model.addAttribute("list", mList);
    return "food";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/register")
  public String register(Model model) {
    UserDto user = new UserDto();
    model.addAttribute("user", user);
    return "register";
  }

  @PostMapping("/register")
  public String registerHandle(Model model, UserDto user) {
    try {
      userService.save(user);
      return "redirect:/index";
    } catch (Exception e) {
      return "register";
    }

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