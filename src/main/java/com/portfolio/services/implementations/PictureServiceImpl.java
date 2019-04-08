package com.portfolio.services.implementations;

import com.portfolio.converters.picture.PictureEntityToPicture;
import com.portfolio.converters.picture.PictureToPictureEntity;
import com.portfolio.converters.portfolio_post.PortfolioPostEntityToPortfolioPost;
import com.portfolio.converters.portfolio_post.PortfolioPostToPortfolioPostEntity;
import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import com.portfolio.models.Picture;
import com.portfolio.models.PortfolioPost;
import com.portfolio.repositories.PictureRepository;
import com.portfolio.repositories.PostRepository;
import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
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
  private final PostRepository postRepository;
  private final PostService portfolioPostService;


  public PictureServiceImpl(PictureRepository repository,
                            PostRepository postRepository,
                            PostService portfolioPostService) {
    this.repository = repository;
    this.postRepository = postRepository;
    this.portfolioPostService = portfolioPostService;
  }

  @Override
  public Set<Picture> getAllPictures() {
    Set<Picture> pictures = new HashSet<>();

    repository.findAll().iterator().forEachRemaining(pictures::add);

    return pictures;
  }

  @Override
  @Transactional
  public Picture getPictureById(Long id) {
    Optional<Picture> entity = repository.findById(id);
    return toPicture.convert(entity.orElse(null));
  }

  @Override
  @Transactional
  public Picture addPicture(Long postId, MultipartFile file) {

    try {
      Post post = postRepository.findById(postId).get();

      Byte[] byteObjects = new Byte[file.getBytes().length];

      int i = 0;
      for (byte b : file.getBytes()){
        byteObjects[i++] = b;
      }

      Picture picture = new Picture();
      picture.setPicture(byteObjects);

      post.getPictures().add(picture);

      postRepository.save(post);

      return toPicture.convert(picture);

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

    Optional<Post> post = postRepository.findById(id);

    PortfolioPost portfolioPost = toPortfolioPost.convert(post.orElse(null));

    Optional<Picture> savedPicture = Objects.requireNonNull(portfolioPost).getPictureEntities()
            .stream().filter(picture -> picture.getId().equals(1L)).findFirst();

    return savedPicture.orElse(null);
  }
}
