package com.portfolio.controllers;

import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 *
 */
@Slf4j
@Controller
public class PostController {

  private final PostService postService;
  private final PictureService pictureService;

  public PostController(PostService postService, PictureService pictureService) {
    this.postService = postService;
    this.pictureService = pictureService;
  }

  @GetMapping({"post/{id}", "/index.html/post/{id}", "/index/post/{id}"})
  public String getDetails(@PathVariable Long id, Model model) {

    model.addAttribute("images", pictureService.getAllPictures(id));
    model.addAttribute("post", this.postService.getPostById(id));
    return "post";
  }
}
