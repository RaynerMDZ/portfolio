package com.portfolio.services.implementations;

import com.portfolio.converters.picture.PictureEntityToPicture;
import com.portfolio.converters.picture.PictureToPictureEntity;
import com.portfolio.converters.portfolio_post.PortfolioPostEntityToPortfolioPost;
import com.portfolio.converters.portfolio_post.PortfolioPostToPortfolioPostEntity;
import com.portfolio.entities.PictureEntity;
import com.portfolio.models.Picture;
import com.portfolio.models.PortfolioPost;
import com.portfolio.repositories.PictureRepository;
import com.portfolio.repositories.PortfolioPostRepository;
import com.portfolio.services.PictureService;
import com.portfolio.services.PortfolioPostService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PictureServiceImpl implements PictureService {

  private final PictureRepository repository;
  private final PortfolioPostRepository portfolioPostRepository;
  private final PortfolioPostEntityToPortfolioPost toPortfolioPost;
  private final PortfolioPostToPortfolioPostEntity toPortfolioPostEntity;
  private final PictureToPictureEntity toPictureEntity;
  private final PictureEntityToPicture toPicture;
  private final PortfolioPostService portfolioPostService;

  public PictureServiceImpl(PictureRepository repository, PortfolioPostRepository portfolioPostRepository, PortfolioPostEntityToPortfolioPost toPortfolioPost, PortfolioPostToPortfolioPostEntity toPortfolioPostEntity, PictureToPictureEntity toPictureEntity, PictureEntityToPicture toPicture, PortfolioPostService portfolioPostService) {
    this.repository = repository;
    this.portfolioPostRepository = portfolioPostRepository;
    this.toPortfolioPost = toPortfolioPost;
    this.toPortfolioPostEntity = toPortfolioPostEntity;
    this.toPictureEntity = toPictureEntity;
    this.toPicture = toPicture;
    this.portfolioPostService = portfolioPostService;
  }

  @Override
  public Set<PictureEntity> getAllPictures() {
    Set<PictureEntity> pictures = new HashSet<>();

    repository.findAll().iterator().forEachRemaining(pictures::add);

    return pictures;
  }

  @Override
  public Picture getPictureById(Long id) {
    Optional<PictureEntity> entity = repository.findById(id);
    return toPicture.convert(entity.orElse(null));
  }

  @Override
  public Picture addPicture(Long postId, Byte[] picture) {

    PortfolioPost post = portfolioPostService.getPostById(postId);

    Picture pictureToSave = new Picture();
    pictureToSave.setPicture(picture);
    pictureToSave.setPostId(post.getId());

    post.getPictureEntities().add(pictureToSave);

    PictureEntity pictureSaved = repository.save(pictureToSave);
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
    Optional<PortfolioPost> post = portfolioPos;

  }
}
