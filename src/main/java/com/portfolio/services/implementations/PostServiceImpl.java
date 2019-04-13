package com.portfolio.services.implementations;

import com.portfolio.entities.Post;
import com.portfolio.repositories.PostRepository;
import com.portfolio.services.PostService;
import javassist.bytecode.annotation.NoSuchClassError;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 */
@Service
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;

  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  /**
   *
   * @return Set<Post>
   */
  @Override
  public List<Post> getAllPosts() {

    List<Post> posts = new ArrayList<>();

    try {
      postRepository.findAll().iterator().forEachRemaining(posts::add);

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

    if (!posts.isEmpty()) {
      return posts;
    }
    return null;
  }

  /**
   *
   * @param id
   * @return Post
   */
  @Override
  public Post getPostById(Long id) {

    if (id != null) {
      Optional<Post> postOptional = postRepository.findById(id);
      if (postOptional.isPresent()) {
        return postOptional.orElse(null);
      }
    }
    return null;
  }

  /**
   *
   * @param post
   * @return Post
   */
  @Override
  public Post createPost(Post post) {

    List<Post> posts = getAllPosts();

    if (post != null) {

      post.setCreatedDate(new Date());
      post.setModifiedDate(new Date());

      if (!posts.contains(post)) {

        try {
          return postRepository.save(post);
        } catch (NoSuchClassError e) {
          e.printStackTrace();
          return null;
        }
      }
      return null;
    }
    return null;
  }

  /**
   *
   * @param post
   * @return Post
   */
  @Override
  public Post updatePost(Post post) {

    Optional<Post> exist = getAllPosts().stream()
            .filter(p -> p.getId().equals(post.getId())).findFirst();

    if (exist.isPresent()) {
      post.setModifiedDate(new Date());

      try {
        return postRepository.save(post);
      } catch (RuntimeException e) {
        e.printStackTrace();
        return null;
      }
    }
    return null;
  }

  /**
   *
   * @param id
   * @return booelan
   */
  @Override
  public boolean deletePostById(Long id) {

    if (id != null) {
      try {
        postRepository.deleteById(id);
        return true;
      } catch (RuntimeException e) {
        e.printStackTrace();
        return false;
      }
    }
    return false;
  }

  /**
   *
   * @param id
   * @return boolean
   */
  @Override
  public boolean hidePost(Long id) {

    Post post = getPostById(id);

    if (!post.isHidden()) {
      post.setHidden(true);
      return true;
    } else {
      post.setHidden(false);
      return false;
    }
  }
}
