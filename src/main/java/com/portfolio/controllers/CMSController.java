package com.portfolio.controllers;

import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/cms")
public class CMSController {

  private final PostService postService;
  private final PictureService pictureService;

  public CMSController(PostService postService, PictureService pictureService) {
    this.postService = postService;
    this.pictureService = pictureService;
  }

  @GetMapping
  @RequestMapping
  public String createPost() {

    return "fragments/portfolio/{id}/post";
  }

  public String updatePost() {

    return "fragments/portfolio/{id}/post";
  }

  public String deletePost() {

    return "fragments/portfolio/{id}/post";
  }

  public String hidePost() {

    return "fragments/portfolio/{id}/post";
  }

  public String addPicture() {

    return "fragments/portfolio/{id}/post";
  }

  public String updatePicture() {

    return "fragments/portfolio/{id}/post";
  }

  public String deletePicture() {

    return "fragments/portfolio/{id}/post";
  }

  public String hidePicture() {

    return "fragments/portfolio/{id}/post";
  }
}
