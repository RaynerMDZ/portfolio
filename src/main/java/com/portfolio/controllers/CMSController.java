package com.portfolio.controllers;

import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cms")
public class CMSController {

  private final PostService postService;
  private final PictureService pictureService;

  public CMSController(PostService postService, PictureService pictureService) {
    this.postService = postService;
    this.pictureService = pictureService;
  }

  @PostMapping
  @RequestMapping("/create-post")
  public String createPost(@RequestBody Post post, Model model) {

    return "fragments/portfolio/{id}/post";
  }

  @PutMapping
  @RequestMapping("/update-post")
  public String updatePost(@RequestBody Post post, Model model) {

    return "fragments/portfolio/{id}/post";
  }

  @DeleteMapping
  @RequestMapping("/delete-post/{id}")
  public String deletePost(@PathVariable Long id, Model model) {

    return "redirects:/fragments/portfolio";
  }

  @PutMapping
  @RequestMapping("/hide-post/{id}")
  public String hidePost(@PathVariable Long id, Model model) {

    return "redirects:/fragments/portfolio";
  }

  @PostMapping
  @RequestMapping("/add-picture/{id}")
  public String addPicture(@PathVariable Long id, @RequestBody Picture picture, Model model) {

    return "fragments/portfolio/{id}/post";
  }

  @PutMapping
  @RequestMapping("/update-picture/{id}")
  public String updatePicture(@PathVariable Long id, @RequestBody Picture picture, Model model) {

    return "fragments/portfolio/{id}/post";
  }

  @DeleteMapping
  @RequestMapping("/delete-picture/{id}")
  public String deletePicture(@PathVariable Long id, Model model) {

    return "redirects:/fragments/portfolio/{id}/post";
  }

  @PutMapping
  @RequestMapping("/hide-picture/{id}")
  public String hidePicture(@PathVariable Long id, Model model) {

    return "redirects:/fragments/portfolio/{id}/post";
  }
}
