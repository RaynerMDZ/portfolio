package com.portfolio.controllers;

import com.portfolio.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Slf4j
@Controller
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  @RequestMapping({"post/{id}", "/index.html/post/{id}", "/index/post/{id}"})
  public String getDetails(@PathVariable Long id, Model model) {

    model.addAttribute("post", this.postService.getPostById(id));

    return "post";
  }
}
