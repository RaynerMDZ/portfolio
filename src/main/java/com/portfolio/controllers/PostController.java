package com.portfolio.controllers;

import com.portfolio.entities.Comment;
import com.portfolio.services.CommentService;
import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 *
 */
@Slf4j
@Controller
public class PostController {

  private final PostService postService;
  private final PictureService pictureService;
  private final CommentService commentService;

  public PostController(PostService postService, PictureService pictureService, CommentService commentService) {
    this.postService = postService;
    this.pictureService = pictureService;
    this.commentService = commentService;
  }

  @GetMapping
  @RequestMapping({"/post/{id}"})
  public String getDetails(@PathVariable Long id, Model model) {

    model.addAttribute("postID", id);
    model.addAttribute("comment", new Comment());
    model.addAttribute("post", this.postService.getPostById(id));
    return "post";
  }


}
