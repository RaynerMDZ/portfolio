package com.portfolio.services.implementations;

import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import com.portfolio.repositories.PostRepository;
import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import com.portfolio.services.implementations.azure.AzurePictureServiceImpl;
import javassist.bytecode.annotation.NoSuchClassError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * This class implements methods from the Post Service.
 * It has all business logic for Post objects.
 */
@Service
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;
  private final PictureService pictureService;

  public PostServiceImpl(PostRepository postRepository, PictureService pictureService) {
    this.postRepository = postRepository;
    this.pictureService = pictureService;
  }

  /**
   * Fetch all posts inside the database and return them as a list.
   * @return List<Post> with all post in the database.
   */
  @Override
  public List<Post> getAllPosts() {

    List<Post> posts = new ArrayList<>();

    try {
      postRepository.findAll().iterator().forEachRemaining(posts::add);

      if (!posts.isEmpty()) {
        return posts;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return null;
  }

  /**
   * Takes an id and search through the posts list. If a post if the same id is found, then it's returned.
   * Otherwise a null is returned.
   * @param id
   * @return the found post.
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
   * Creates a post from an object passed to the method.
   * @param post
   * @return the created post object.
   */
  @Override
  public Post createPost(Post post) {

    if (post != null) {

      post.setCreatedDate(new Date());
      post.setModifiedDate(new Date());
      post.setHidden(false);

      try {
        return postRepository.save(post);
      } catch (NoSuchClassError e) {
        e.printStackTrace();
        return null;
      }
    }
    return null;
  }

  /**
   * Takes an existing post and sets a new modified date. Then, it is saved.
   * @param post
   * @return saved post object.
   */
  @Override
  public Post updatePost(Post post) {

    Optional<Post> exist = Optional.empty();

    if (this.exist(post.getId())) {
      exist = getAllPosts().stream()
              .filter(p -> p.getId().equals(post.getId())).findFirst();
    }

    if (exist.isPresent()) {
      post.setHidden(exist.get().isHidden());
      post.setCreatedDate(exist.get().getCreatedDate());
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
   * Takes the id of a post. if the id exist, then the post is deleted.
   * @param id
   * @return booelan
   */
  @Override
  public boolean deletePostById(Long id) {

    if (exist(id)) {
      try {

        Post post = getPostById(id);

        for (Picture picture : post.getPictures()) {
          pictureService.deletePictureById(picture.getId());
        }

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
   * Takes an id of a post. Then, it check whether isHidden() is true or false.
   * If true then it changes to false. If false then it changes to true.
   * @param id
   * @return boolean
   */
  @Override
  public boolean hidePost(Long id) {

    if (exist(id)) {
      Post post = getPostById(id);

      if (!post.isHidden()) {
        post.setHidden(true);
        this.updatePost(post);
        return true;

      } else {
        post.setHidden(false);
        this.updatePost(post);
        return false;
      }
    }
    return false;
  }

  /**
   *  Check if a post exists.
   * @param id
   * @return a boolean
   */
  private boolean exist(Long id) {
    Post existing = this.getPostById(id);
    return existing != null;
  }
}
