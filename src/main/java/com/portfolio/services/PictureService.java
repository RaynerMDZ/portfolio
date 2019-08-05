package com.portfolio.services;

import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 */
public interface PictureService {

  List<Picture> getAllPictures(Long id);
  Picture getPictureById(Long id);
  boolean savePicture(Long postId, MultipartFile file);
  boolean savePictures(Long postId, MultipartFile[] file);
  boolean deletePictureById(Long id);
  boolean hidePicture(Long id);
  String generateString();
  String getFileExtension(String name);
}
