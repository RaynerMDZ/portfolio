package com.portfolio.services;


import com.portfolio.entities.Comment;

import java.util.Set;

/**
 *
 */
public interface CommentService {

  Set<Comment> getAllComments();
  Comment createComment(Comment comment);
  Comment getCommentById(Long id);
  boolean deleteCommentById(Long id);
}
