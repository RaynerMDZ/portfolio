package com.portfolio.services;

import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 */
public interface PictureService {

  List<String> getAllPictures(Long id);
  Picture getPictureById(Long id);
  void saveImage(Long recipeId, MultipartFile file);
  boolean deletePictureById(Long id);
  boolean hidePicture(Long id);
}
