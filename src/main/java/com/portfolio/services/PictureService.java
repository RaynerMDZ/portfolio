package com.portfolio.services;

import com.portfolio.entities.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

/**
 *
 */
public interface PictureService {

  Set<Picture> getAllPictures();
  Picture getPictureById(Long id);
  Picture addPicture(Long postId, MultipartFile file);
  Picture updatePicture(Picture picture);
  boolean deletePictureById(Long id);
  boolean hidePicture(Long id);
  Picture findFirstPicture(Long postId);
}
