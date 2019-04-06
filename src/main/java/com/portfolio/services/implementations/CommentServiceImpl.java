package com.portfolio.services.implementations;

import com.portfolio.models.Comment;
import com.portfolio.repositories.CommentRepository;
import com.portfolio.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

  private final CommentRepository repository;

  public CommentServiceImpl(CommentRepository repository) {
    this.repository = repository;
  }

  @Override
  public Iterable<Comment> getAllComments() {
    return null;
  }

  @Override
  public Optional<Comment> createComment(Comment comment) {
    return Optional.empty();
  }

  @Override
  public Optional<Comment> getCommentById(Long id) {
    return Optional.empty();
  }

  @Override
  public boolean deleteCommentById(Long id) {
    return false;
  }
}
