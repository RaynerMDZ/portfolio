package com.portfolio.services;

import com.portfolio.entities.PostEntity;
import com.portfolio.models.PortfolioPost;

import java.util.Set;

public interface PortfolioPostService {

  Set<PostEntity> getAllPosts();
  PortfolioPost getPostById(Long id);
  PortfolioPost createPost(PortfolioPost post);
  PortfolioPost updatePost(PortfolioPost post);
  boolean deletePostById(Long id);
  boolean hidePost(Long id);
}
