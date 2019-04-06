package com.portfolio.services.implementations;

import com.portfolio.models.PortfolioPost;
import com.portfolio.services.PortfolioPostService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PortfolioPostServiceImpl implements PortfolioPostService {

  @Override
  public Iterable<PortfolioPost> getAllPosts() {
    return null;
  }

  @Override
  public Optional<PortfolioPost> getPostById(Long id) {
    return Optional.empty();
  }

  @Override
  public Optional<PortfolioPost> createPost(PortfolioPost post) {
    return Optional.empty();
  }

  @Override
  public Optional<PortfolioPost> updatePost(PortfolioPost post) {
    return Optional.empty();
  }

  @Override
  public boolean deletePostById(Long id) {
    return false;
  }

  @Override
  public boolean hidePost(Long id) {
    return false;
  }
}
