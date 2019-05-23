package com.portfolio.services.implementations;

import com.portfolio.Util.CustomException;
import com.portfolio.entities.Comment;
import com.portfolio.entities.Post;
import com.portfolio.repositories.CommentRepository;
import com.portfolio.services.CommentService;
import com.portfolio.services.PostService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 */
@Service
public class CommentServiceImpl implements CommentService {

  private final CommentRepository repository;
  private final PostService postService;

  public CommentServiceImpl(CommentRepository repository, PostService postService) {
    this.repository = repository;
    this.postService = postService;
  }

  /**
   *
   * @return List<Comment>
   */
  @Override
  public List<Comment> getAllComments() {
    try {
      // Checks if the list returned is not empty.
      List<Comment> comments = getAllComments();
      if (comments != null) {
        return comments;
      }
      throw new CustomException("List was returned empty.");

    } catch (CustomException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  /**
   *
   * @param comment
   * @return Comment
   */
  @Override
  public Comment createComment(Comment comment, Long postID) {

    Post post = postService.getPostById(postID);

    if (post != null) {
      if (!exist(comment.getId())) {

        try {
          comment.setCreationDate(new Date());
          comment.setPost(post);
          Comment savedComment = repository.save(comment);

          if (savedComment != null) {
            return savedComment;
          }
          throw new CustomException("Cannot create comment. Null object returned.");

        } catch (CustomException e) {
          System.out.println(e.getMessage());
          e.printStackTrace();
          return null;
        }
      }
    }
    return null;
  }

  /**
   *
   * @param id
   * @return Comment
   */
  @Override
  public Comment getCommentById(Long id) {

    try {
      Comment foundComment = repository.findById(id).orElse(null);
      if (foundComment != null) {
        return foundComment;
      }
      throw new NoSuchElementException();

    } catch (NoSuchElementException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   *
   * @param id
   * @return boolean
   */
  @Override
  public boolean deleteCommentById(Long id) {
    Comment comment = this.getCommentById(id);

    if (comment != null) {
      try {
        repository.delete(comment);
        return true;
      } catch (NoSuchElementException e) {
        e.printStackTrace();
        return false;
      }
    }
    return false;
  }

  private boolean exist(Long id) {
    Comment existing = this.getCommentById(id);
    return existing != null;
  }
}
