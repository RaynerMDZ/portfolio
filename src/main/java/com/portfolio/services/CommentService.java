package com.portfolio.services;


import com.portfolio.entities.Comment;

import java.util.List;

/**
 *
 */
public interface CommentService {

  List<Comment> getAllComments();
  Comment createComment(Comment comment);
  Comment getCommentById(Long id);
  boolean deleteCommentById(Long id);
}
