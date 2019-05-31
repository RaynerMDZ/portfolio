package com.portfolio.services.implementations;

import com.portfolio.Util.FileUploader;
import com.portfolio.Util.Util;
import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import com.portfolio.repositories.PictureRepository;
import com.portfolio.repositories.PostRepository;
import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
   * @return list<String>
   */
  @Override
  public List<Picture> getAllPictures(Long id) {

    return postService.getPostById(id).getPictures();
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

  @Override
  public boolean savePicture(Long postId, MultipartFile file) {

    String extension = FileUploader.getFileExtension(file.getOriginalFilename());

    String name = FileUploader.generateString();
    Post post = postService.getPostById(postId);
    Picture picture = new Picture();
    picture.setHidden(false);
    picture.setPictureString(Util.IMAGE_URL + name + extension);
    picture.setPost(post);

    boolean success = FileUploader.pictureUploader(Util.UPLOAD_DIRECTORY, file, name);

    if (success) {
      pictureRepository.save(picture);
      return true;
    }

    return false;
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

//  @Override
//  @Transactional
//  public void saveImage(Long id, MultipartFile file) {
//
//    if (file.isEmpty()) {
//      return;
//    }
//
//    log.debug("Received a file");
//
//    try {
//      Post post = postService.getPostById(id);
//      Picture picture = new Picture();
//
//      byte[] bytes = new byte[file.getBytes().length];
//
//      int i = 0;
//
//      for (byte b : file.getBytes()) {
//        bytes[i++] = b;
//      }
//      String encoded = Base64.encodeBase64String(bytes);
//
//      picture.setPictureBytes(bytes);
//      picture.setPost(post);
//      picture.setHidden(false);
//      picture.setPictureString(encoded);
//      post.getPictures().add(picture);
//      Post savedPost = postService.updatePost(post);
//
//      if (savedPost != null) {
//        log.debug("Saved to database.");
//        return;
//      }
//      throw new IOException("Post is null. Not saved.");
//
//    } catch (IOException e) {
//      log.error("Error occurred.", e);
//      e.printStackTrace();
//    }
//  }
//

}










