package com.portfolio.services.implementations;

import com.portfolio.entities.Post;
import com.portfolio.repositories.PictureRepository;
import com.portfolio.repositories.PostRepository;
import com.portfolio.services.PictureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Rayner MDZ
 */
class PostServiceImplTest {

  private PostServiceImpl postServiceImpl;

  @Mock
  private PostRepository postRepository;

  @Mock
  private PictureService pictureService;

  @Mock
  private PictureRepository pictureRepository;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    postServiceImpl = new PostServiceImpl(postRepository, pictureService);
  }

  @Test
  void getAllPosts() {

    Post post = new Post();
    ArrayList<Post> posts = new ArrayList<>();
    posts.add(post);

    when(postServiceImpl.getAllPosts()).thenReturn(posts);

    List<Post> postsArray = postServiceImpl.getAllPosts();

    assertEquals(postsArray.size(), 1);
    verify(postRepository, times(1)).findAll();
    verify(postRepository, never()).findById(anyLong());
  }

  @Test
  void getPostById() {

    Post post = new Post();
    post.setId(1L);
    Optional<Post> postOptional = Optional.of(post);

    when(postRepository.findById(anyLong())).thenReturn(postOptional);

    Post postReturned = postServiceImpl.getPostById(1L);

    assertNotNull("Null post returned", postReturned);
    verify(postRepository, times(1)).findById(anyLong());
    verify(postRepository, never()).findAll();
  }

  @Test
  void createPost() {
  }

  @Test
  void updatePost() {
  }

  @Test
  void deletePostById() {
  }

  @Test
  void hidePost() {
  }
}
