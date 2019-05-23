package com.portfolio.controllers;

import com.portfolio.entities.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
  public String createComment(@ModelAttribute("comment") Comment comment) {


    return "comment/create-comment";
  }

  @PostMapping
  @RequestMapping("/delete-comment")
  public String deleteComment() {

    return "comment/create-comment";
  }
}
