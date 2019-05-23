package com.portfolio.services;

import com.portfolio.entities.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 */
public interface PictureService {

  List<Picture> getAllPictures();
  Picture getPictureById(Long id);
  void uploadPictures(Long postId, MultipartFile[] file);
  Boolean uploadPicture(Long postId, MultipartFile file);
  Picture updatePicture(Picture picture);
  boolean deletePictureById(Long id);
  boolean hidePicture(Long id);
  Picture findFirstPicture(Long postId);
}
