package com.portfolio.services.implementations;

import com.portfolio.converters.portfolio_post.PortfolioPostEntityToPortfolioPost;
import com.portfolio.converters.portfolio_post.PortfolioPostToPortfolioPostEntity;
import com.portfolio.entities.PostEntity;
import com.portfolio.models.PortfolioPost;
import com.portfolio.repositories.PortfolioPostRepository;
import com.portfolio.services.PortfolioPostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PortfolioPostServiceImpl implements PortfolioPostService {

  private final PortfolioPostRepository portfolioPostRepository;
  private final PortfolioPostToPortfolioPostEntity toPortfolioPostEntity;
  private final PortfolioPostEntityToPortfolioPost toPortfolioPost;

  public PortfolioPostServiceImpl(PortfolioPostRepository portfolioPostRepository,
                                  PortfolioPostToPortfolioPostEntity toPortfolioPostEntity,
                                  PortfolioPostEntityToPortfolioPost toPortfolioPost) {
    this.portfolioPostRepository = portfolioPostRepository;
    this.toPortfolioPostEntity = toPortfolioPostEntity;
    this.toPortfolioPost = toPortfolioPost;
  }

  @Override
  public Set<PostEntity> getAllPosts() {
    Set<PostEntity> posts = new HashSet<>();
    portfolioPostRepository.findAll().iterator().forEachRemaining(posts::add);
    return posts;
  }

  @Override
  @Transactional
  public PortfolioPost getPostById(Long id) {
    Optional<PortfolioPost> post = portfolioPostRepository.findAll()
            .stream().filter(p -> p.getId().equals(id))
            .map(p -> toPortfolioPost.convert(p)).findFirst();

    return post.orElse(null);
  }

  @Override
  @Transactional
  public PortfolioPost createPost(PortfolioPost post) {

    PortfolioPost portfolioPost = getPostById(post.getId());

    if (portfolioPost != null) {
      PostEntity entity = toPortfolioPostEntity.convert(post);

      PostEntity savedEntity = portfolioPostRepository.save(entity);

      return toPortfolioPost.convert(savedEntity);
    }
    return null;
  }

  @Override
  public PortfolioPost updatePost(PortfolioPost post) {
    return null;
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
