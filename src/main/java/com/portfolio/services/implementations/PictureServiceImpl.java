package com.portfolio.services.implementations;

import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import com.portfolio.repositories.PictureRepository;
import com.portfolio.repositories.PostRepository;
import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 *
 */
@Slf4j
@Service
public class PictureServiceImpl implements PictureService {

  private final PictureRepository pictureRepository;
  private final PostRepository postRepository;
  private final PostService postService;


  public PictureServiceImpl(PictureRepository pictureRepository,
                            PostRepository postRepository,
                            PostService postService) {
    this.pictureRepository = pictureRepository;
    this.postRepository = postRepository;
    this.postService = postService;
  }

  /**
   *
   * @return Set<Pictures>
   */
  @Override
  public Set<Picture> getAllPictures() {

    Set<Picture> pictures = new HashSet<>();

    try {
      pictureRepository.findAll().iterator().forEachRemaining(pictures::add);

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

    if (!pictures.isEmpty()) {
      return pictures;
    }
    return null;
  }

  /**
   *
   * @param id
   * @return Picture
   */
  @Override
  public Picture getPictureById(Long id) {

    if (id != null) {
      try {
        Optional<Picture> picture = pictureRepository.findById(id);

        if (picture.isPresent()) {
          return picture.get();
        }

      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }
    return null;
  }

  /**
   *
   * @param postId
   * @param file
   * @return Picture
   */
  @Override
  public Picture addPicture(Long postId, MultipartFile file) {

    try {
      Post post = postRepository.findById(postId).orElse(null);

      Byte[] byteObjects = new Byte[file.getBytes().length];

      int i = 0;
      for (byte b : file.getBytes()){
        byteObjects[i++] = b;
      }

      Picture picture = new Picture();
      picture.setPicture(byteObjects);
      post.getPictures().add(picture);
      postRepository.save(post);

    } catch (IOException e) {
      //todo handle better
      log.error("Error occurred", e);

      e.printStackTrace();
      return null;
    }
    return null;
  }

  /**
   *
   * @param picture
   * @return Picture
   */
  @Override
  public Picture updatePicture(Picture picture) {
    return null;
  }

  /**
   *
   * @param id
   * @return boolean
   */
  @Override
  public boolean deletePictureById(Long id) {
    return false;
  }

  /**
   *
   * @param id
   * @return boolean
   */
  @Override
  public boolean hidePicture(Long id) {

    Picture picture = null;
    if (id != null) {
      picture = getPictureById(id);
    }

    // If picture is not null.
    if (picture != null) {
      // If isHidden is true then switch it to false.
      if (picture.isHidden()) {
        picture.setHidden(false);
        return false;

      } else {
        // If isHidden is false then switch it to true.
        picture.setHidden(true);
        return true;
      }
    }
    return false;
  }

  /**
   *
   * @param postId
   * @return Picture
   */
  @Override
  public Picture findFirstPicture(Long postId) {

    Post post = null;
    if (postId != null) {
      post = postService.getPostById(postId);
    }

    Optional<Picture> fistPicture = null;
    try {
      fistPicture = Objects.requireNonNull(post).getPictures().stream()
              .filter(picture -> picture.getId().equals(1L)).findFirst();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

    if (fistPicture.isPresent()) {
      return fistPicture.orElse(null);
    }
    return null;
  }
}
