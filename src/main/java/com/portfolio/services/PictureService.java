package com.portfolio.services;

import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 */
public interface PictureService {

  List<Picture> getAllPictures();
  Picture getPictureById(Long id);
  boolean uploadPictures(Long postId, MultipartFile[] files);
  boolean uploadPictures(Post post, MultipartFile[] files);
  boolean uploadPicture(Long postId, MultipartFile file);
  boolean uploadPicture(Post post, MultipartFile file);
  Picture updatePicture(Picture picture);
  boolean deletePictureById(Long id);
  boolean hidePicture(Long id);
}
