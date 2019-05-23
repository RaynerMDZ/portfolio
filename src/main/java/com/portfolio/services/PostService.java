package com.portfolio.services;

import com.portfolio.entities.Post;

import java.util.List;

/**
 *
 */
public interface PostService {

  List<Post> getAllPosts();
  Post getPostById(Long id);
  Post createPost(Post post);
  Post updatePost(Post post);
  boolean deletePostById(Long id);
  boolean hidePost(Long id);
  boolean exist(Long id);
}
