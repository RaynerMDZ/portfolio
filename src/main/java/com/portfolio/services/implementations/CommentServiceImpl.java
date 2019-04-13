package com.portfolio.services.implementations;

import com.portfolio.entities.Comment;
import com.portfolio.repositories.CommentRepository;
import com.portfolio.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 *
 */
@Service
public class CommentServiceImpl implements CommentService {

  private final CommentRepository repository;

  public CommentServiceImpl(CommentRepository repository) {
    this.repository = repository;
  }

  /**
   *
   * @return Set<Comment>
   */
  @Override
  public List<Comment> getAllComments() {
    return repository.findAll();
  }

  /**
   *
   * @param comment
   * @return Comment
   */
  @Override
  public Comment createComment(Comment comment) {
    return null;
  }

  /**
   *
   * @param id
   * @return Comment
   */
  @Override
  public Comment getCommentById(Long id) {
    return null;
  }

  /**
   *
   * @param id
   * @return boolean
   */
  @Override
  public boolean deleteCommentById(Long id) {
    return false;
  }
}
