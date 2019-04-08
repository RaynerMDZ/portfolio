package com.portfolio.services.implementations;

import com.portfolio.converters.picture.PictureEntityToPicture;
import com.portfolio.converters.picture.PictureToPictureEntity;
import com.portfolio.converters.portfolio_post.PortfolioPostEntityToPortfolioPost;
import com.portfolio.converters.portfolio_post.PortfolioPostToPortfolioPostEntity;
import com.portfolio.entities.PictureEntity;
import com.portfolio.entities.PostEntity;
import com.portfolio.models.Picture;
import com.portfolio.models.PortfolioPost;
import com.portfolio.repositories.PictureRepository;
import com.portfolio.repositories.PortfolioPostRepository;
import com.portfolio.services.PictureService;
import com.portfolio.services.PortfolioPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class PictureServiceImpl implements PictureService {

  private final PictureRepository repository;
  private final PortfolioPostRepository portfolioPostRepository;
  private final PortfolioPostEntityToPortfolioPost toPortfolioPost;
  private final PortfolioPostToPortfolioPostEntity toPortfolioPostEntity;
  private final PictureToPictureEntity toPictureEntity;
  private final PictureEntityToPicture toPicture;
  private final PortfolioPostService portfolioPostService;

  public PictureServiceImpl(PictureRepository repository,
                            PortfolioPostRepository portfolioPostRepository,
                            PortfolioPostEntityToPortfolioPost toPortfolioPost,
                            PortfolioPostToPortfolioPostEntity toPortfolioPostEntity,
                            PictureToPictureEntity toPictureEntity,
                            PictureEntityToPicture toPicture,
                            PortfolioPostService portfolioPostService) {
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
  @Transactional
  public Picture getPictureById(Long id) {
    Optional<PictureEntity> entity = repository.findById(id);
    return toPicture.convert(entity.orElse(null));
  }

  @Override
  @Transactional
  public Picture addPicture(Long postId, MultipartFile file) {

    try {
      PostEntity post = portfolioPostRepository.findById(postId).get();

      Byte[] byteObjects = new Byte[file.getBytes().length];

      int i = 0;
      for (byte b : file.getBytes()){
        byteObjects[i++] = b;
      }

      PictureEntity pictureEntity = new PictureEntity();
      pictureEntity.setPicture(byteObjects);

      post.getPictures().add(pictureEntity);

      portfolioPostRepository.save(post);

      return toPicture.convert(pictureEntity);

    } catch (IOException e) {
      //todo handle better
      log.error("Error occurred", e);

      e.printStackTrace();
      return null;
    }

  }

  @Override
  public Picture updatePicture(Picture picture) {
    return null;
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
  @Transactional
  public Picture findFirstPicture(Long id) {

    Optional<PostEntity> post = portfolioPostRepository.findById(id);

    PortfolioPost portfolioPost = toPortfolioPost.convert(post.orElse(null));

    Optional<Picture> savedPicture = Objects.requireNonNull(portfolioPost).getPictureEntities()
            .stream().filter(picture -> picture.getId().equals(1L)).findFirst();

    return savedPicture.orElse(null);
  }
}
