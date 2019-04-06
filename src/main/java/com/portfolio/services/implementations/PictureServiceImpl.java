package com.portfolio.services.implementations;

import com.portfolio.models.Picture;
import com.portfolio.models.PortfolioPost;
import com.portfolio.repositories.PictureRepository;
import com.portfolio.repositories.PortfolioPostRepository;
import com.portfolio.services.PictureService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

  private final PictureRepository repository;
  private final PortfolioPostRepository portfolioPostRepository;

  public PictureServiceImpl(PictureRepository repository, PortfolioPostRepository portfolioPostRepository) {
    this.repository = repository;
    this.portfolioPostRepository = portfolioPostRepository;
  }

  @Override
  public Iterable<Picture> getAllPictures() {
    return null;
  }

  @Override
  public Optional<Picture> getPictureById(Long id) {
    return Optional.empty();
  }

  @Override
  public Optional<Picture> addPicture(Picture picture) {
    return Optional.empty();
  }

  @Override
  public Optional<Picture> updatePicture(Picture picture) {
    return Optional.empty();
  }

  @Override
  public boolean deletePictureById(Long id) {
    return false;
  }

  @Override
  public boolean hidePicture(Long id) {
    return false;
  }

  @Override
  public Optional<Picture> findFirstPicture(Long id) {
    Optional<PortfolioPost> post = portfolioPostRepository.findById(id);

  }
}
