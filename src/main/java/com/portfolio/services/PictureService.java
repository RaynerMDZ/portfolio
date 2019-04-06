package com.portfolio.services;

import com.portfolio.entities.PictureEntity;
import com.portfolio.models.Picture;

import java.util.Set;

public interface PictureService {

  Set<PictureEntity> getAllPictures();
  Picture getPictureById(Long id);
  Picture addPicture(Long postId, Byte[] picture);
  Picture updatePicture(Picture picture);
  boolean deletePictureById(Long id);
  boolean hidePicture(Long id);
  Picture findFirstPicture(Long id);
}
