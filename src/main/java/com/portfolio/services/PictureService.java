package com.portfolio.services;

import com.portfolio.models.Picture;

import java.util.Optional;

public interface PictureService {

  Iterable<Picture> getAllPictures();
  Optional<Picture> getPictureById(Long id);
  Optional<Picture> addPicture(Picture picture);
  Optional<Picture> updatePicture(Picture picture);
  boolean deletePictureById(Long id);
  boolean hidePicture(Long id);
}
