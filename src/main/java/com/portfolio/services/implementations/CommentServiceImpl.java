package com.portfolio.services.implementations;

import com.portfolio.models.Comment;
import com.portfolio.repositories.CommentRepository;
import com.portfolio.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

  private final CommentRepository repository;

  public CommentServiceImpl(CommentRepository repository) {
    this.repository = repository;
  }

  @Override
  public Set<Comment> getAllComments() {
    return null;
  }

  @Override
  public Comment createComment(Comment comment) {
    return null;
  }

  @Override
  public Comment getCommentById(Long id) {
    return null;
  }

  @Override
  public boolean deleteCommentById(Long id) {
    return false;
  }
}
