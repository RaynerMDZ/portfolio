package com.portfolio.controllers;

import com.portfolio.entities.Comment;
import com.portfolio.services.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

  @RequestMapping("post/{id}/create-comment")
  @PostMapping
  public String createComment(@ModelAttribute("comment") Comment comment, BindingResult bindingResult, @PathVariable String id) {

    if (bindingResult.hasErrors()) {
      return "post";
    }

    if (comment != null) {
      commentService.createComment(comment, Long.valueOf(id));
    }

    return "redirect:/post/" + id;
  }

  @PostMapping
  @RequestMapping("/cms/{id}/delete-comment")
  public String deleteComment(@PathVariable String id) {

    Comment comment = commentService.getCommentById(Long.valueOf(id));

    Long postId = comment.getPost().getId();

    boolean success = commentService.deleteCommentById(comment.getId());

    if (success) return "redirect:/cms/" + postId  + "/edit-post";

    return "error/error-500";
  }
}
