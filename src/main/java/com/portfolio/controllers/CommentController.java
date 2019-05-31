package com.portfolio.controllers;

import com.portfolio.entities.Comment;
import com.portfolio.services.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 */
@Slf4j
@Controller
public class CommentController {

  private final CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @PostMapping("post/{id}/create-comment")
  public String createComment(@ModelAttribute("comment") Comment comment, @PathVariable String id) {

    if (comment != null) {
      commentService.createComment(comment, Long.valueOf(id));
    }

    return "redirect:/post/" + id;
  }

  @PostMapping("/delete-comment")
  public String deleteComment() {

    return "comment/create-comment";
  }
}
