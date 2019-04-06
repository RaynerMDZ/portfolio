package com.portfolio.services;

import com.portfolio.entities.PictureEntity;
import com.portfolio.models.Picture;

import java.util.Optional;

public interface PictureService {

  Iterable<PictureEntity> getAllPictures();
  Optional<PictureEntity> getPictureById(Long id);
  Optional<PictureEntity> addPicture(Picture picture);
  Optional<PictureEntity> updatePicture(Picture picture);
  boolean deletePictureById(Long id);
  boolean hidePicture(Long id);
  Optional<PictureEntity> findFirstPicture(Long id);
}
