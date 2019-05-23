package com.portfolio.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Slf4j
@Controller
public class CommentController {

  @GetMapping
  @RequestMapping("/show-comment")
  public String showComment(Model model) {
    return "comment/create-comment";
  }

  @PostMapping
  @RequestMapping("/create-comment")
  public String createComment() {

    return "comment/create-comment";
  }

  @PostMapping
  @RequestMapping("/delete-comment")
  public String deleteComment() {

    return "comment/create-comment";
  }
}
