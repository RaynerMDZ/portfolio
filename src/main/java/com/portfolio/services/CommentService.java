package com.portfolio.services;

import com.portfolio.models.Comment;

import java.util.Optional;

public interface CommentService {

  Iterable<Comment> getAllComments();
  Optional<Comment> createComment(Comment comment);
  Optional<Comment> getCommentById(Long id);
  boolean deleteCommentById(Long id);
}
