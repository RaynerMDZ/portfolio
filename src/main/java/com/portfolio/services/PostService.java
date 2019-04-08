package com.portfolio.services;

import com.portfolio.entities.Post;

import java.util.Set;

public interface PostService {

  Set<Post> getAllPosts();
  Post getPostById(Long id);
  Post createPost(Post post);
  Post updatePost(Post post);
  boolean deletePostById(Long id);
  boolean hidePost(Long id);
}
