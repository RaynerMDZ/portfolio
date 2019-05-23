package com.portfolio.services;


import com.portfolio.entities.Comment;
import com.portfolio.entities.Post;

import java.util.List;

/**
 *
 */
public interface CommentService {

  List<Comment> getAllComments();
  Comment createComment(Comment comment, Long postID);
  Comment getCommentById(Long id);
  boolean deleteCommentById(Long id);
}
