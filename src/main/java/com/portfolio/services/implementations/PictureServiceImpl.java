package com.portfolio.services.implementations;

import com.portfolio.Util.FileUploader;
import com.portfolio.Util.Util;
import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import com.portfolio.repositories.PictureRepository;
import com.portfolio.repositories.PostRepository;
import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import org.springframework.core.io.ResourceLoader;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 *
 */
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

  @Override
  public boolean savePictures(Long postId, MultipartFile[] file) {
    return false;
  }

  /**
   * @param id
   * @return boolean
   */
  @Override
  public boolean deletePictureById(Long id) {

    Picture picture = this.getPictureById(id);

    try {
      pictureRepository.delete(picture);
      return true;

    } catch (HibernateJdbcException e) {
      e.printStackTrace();
      return false;
    }
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










