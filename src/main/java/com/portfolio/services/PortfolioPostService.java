package com.portfolio.services;

import com.portfolio.models.PortfolioPost;

import java.util.Optional;

public interface PortfolioPostService {

  Iterable<PortfolioPost> getAllPosts();
  Optional<PortfolioPost> getPostById(Long id);
  Optional<PortfolioPost> createPost(PortfolioPost post);
  Optional<PortfolioPost> updatePost(PortfolioPost post);
  boolean deletePostById(Long id);
  boolean hidePost(Long id);
}
