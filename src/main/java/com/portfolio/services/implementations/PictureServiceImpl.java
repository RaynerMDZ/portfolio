package com.portfolio.services.implementations;

import com.portfolio.Util.Util;
import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import com.portfolio.repositories.PictureRepository;
import com.portfolio.repositories.PostRepository;
import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 *
 */
@Slf4j
@Service
public class PictureServiceImpl implements PictureService {

  private final PictureRepository pictureRepository;
  private final PostRepository postRepository;
  private final PostService postService;
  private final ResourceLoader resourceLoader;

  public PictureServiceImpl(PictureRepository pictureRepository,
                            PostRepository postRepository,
                            PostService postService,
                            ResourceLoader resourceLoader) {
    this.pictureRepository = pictureRepository;
    this.postRepository = postRepository;
    this.postService = postService;
    this.resourceLoader = resourceLoader;
  }

  /**
   * @return Set<Pictures>
   */
  @Override
  public List<Picture> getAllPictures() {

    List<Picture> pictures = new ArrayList<>();

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
   * @param postId
   * @param files
   * @return Picture
   */
  @Override
  @Transactional
  public void uploadPictures(Long postId, MultipartFile[] files) {

    // Mock
    //postId = 1L;

    Post post = postService.getPostById(postId);

    if (post != null) {
      Picture picture = new Picture();

      StringBuilder fileNames = new StringBuilder();

      for (MultipartFile file : files) {
        Path fileNameAndPAth = Paths.get(Util.UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(Util.IMAGE_URL + Util.generateString());

        try {
          Files.write(fileNameAndPAth, file.getBytes());
          picture.setPicture(fileNames.toString());
          post.getPictures().add(picture);
          postService.updatePost(post);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Override
  public void uploadPicture(Long postId, MultipartFile file) {

    Post post = postService.getPostById(postId);

    if (post != null) {
      Picture picture = new Picture();

      StringBuilder fileNames = new StringBuilder();

      Path fileNameAndPAth = Paths.get(Util.UPLOAD_DIRECTORY, file.getOriginalFilename());
      fileNames.append(file.getOriginalFilename());

      try {
        Files.write(fileNameAndPAth, file.getBytes());
        picture.setPicture(fileNames.toString());
        post.getPictures().add(picture);
        postService.updatePost(post);

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * @param picture
   * @return Picture
   */
  @Override
  public Picture updatePicture(Picture picture) {
    return null;
  }

  /**
   * @param id
   * @return boolean
   */
  @Override
  public boolean deletePictureById(Long id) {
    return false;
  }

  /**
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
      if (picture.getHidden()) {
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










