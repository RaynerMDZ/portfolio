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
   * @return list<Pictures>
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
  public boolean uploadPictures(Long postId, MultipartFile[] files) {

    Post post = postService.getPostById(postId);

    if (post != null) {
      for (MultipartFile file : files) {
        StringBuilder fileNames = new StringBuilder();

        Path fileNameAndPAth = Paths.get(Util.UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());

        try {
          Files.write(fileNameAndPAth, file.getBytes());

          Picture picture = new Picture();
          picture.setPicture(Util.IMAGE_URL + fileNames.toString());
          picture.setPost(post);
          pictureRepository.save(picture);

        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return true;
    }
    return false;
  }

  @Override
  public boolean uploadPictures(Post post, MultipartFile[] files) {
    if (post != null) {
      for (MultipartFile file : files) {
        StringBuilder fileNames = new StringBuilder();

        Path fileNameAndPAth = Paths.get(Util.UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());

        try {
          Files.write(fileNameAndPAth, file.getBytes());

          Picture picture = new Picture();
          picture.setPicture(Util.IMAGE_URL + fileNames.toString());
          picture.setPost(post);
          pictureRepository.save(picture);

        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return true;
    }
    return false;
  }

  @Override
  public boolean uploadPicture(Long postId, MultipartFile file) {
    // Gets the post that belongs to'postId'
    Post post = postService.getPostById(postId);

    if (post != null) {
      StringBuilder fileNames = new StringBuilder();

      Path fileNameAndPAth = Paths.get(Util.UPLOAD_DIRECTORY, file.getOriginalFilename());
      fileNames.append(file.getOriginalFilename());

      try {
        Files.write(fileNameAndPAth, file.getBytes());

        Picture picture = new Picture();
        picture.setPicture(Util.IMAGE_URL + fileNames.toString());

        picture.setPost(post);
        pictureRepository.save(picture);
        return true;

      } catch (IOException e) {
        e.printStackTrace();
        return false;
      }
    }

    return false;
  }

  @Override
  public boolean uploadPicture(Post post, MultipartFile file) {

    if (post != null) {
      StringBuilder fileNames = new StringBuilder();

      Path fileNameAndPAth = Paths.get(Util.UPLOAD_DIRECTORY, file.getOriginalFilename());
      fileNames.append(file.getOriginalFilename());

      try {
        Files.write(fileNameAndPAth, file.getBytes());

        Picture picture = new Picture();
        picture.setPicture(Util.IMAGE_URL + fileNames.toString());

        picture.setPost(post);
        pictureRepository.save(picture);
        return true;

      } catch (IOException e) {
        e.printStackTrace();
        return false;
      }
    }

    return false;
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


}










