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
 * This class refers to the business logic related to comments.
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
   * Returns a List of comments if the list created is not null.
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
   * First: finds the post where the comment is going to be created.
   * Second: If the Post exist, a new creation date is going to be assigned to the comment.
   * Then, the Post is assigned to the comment in order to take its id.
   * Third: Attempts to save the Comment object with the fetched data. If the save method returns
   * a value not null, the Comment object is returned.
   * @param comment
   * @return the created Comment object.
   */
  @Override
  public Comment createComment(Comment comment, Long postID) {

    if (comment == null) return null;

    if (comment.getBody().equals("")) return null;

    if (comment.getName().equals("")) return null;

    Post post = postService.getPostById(postID);

    if (post != null) {
        try {
          comment.setId(null);
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
    return null;
  }

  /**
   * Simply looks for a Comment object inside the List of objects. If it is found, it returns it.
   * @param id
   * @return found Comment object.
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
   * Uses the method getCommentById() to search for the Comment object related to the id provided.
   * Then, it tries to delete the Comment object. If so, the method returns true, otherwise throws a
   * exception and returns false.
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

  /**
   * Helper method to check if a Comment exist.
   * @param id
   * @return true or false.
   */
  private boolean exist(Long id) {
    Comment existing = this.getCommentById(id);
    return existing != null;
  }
}
