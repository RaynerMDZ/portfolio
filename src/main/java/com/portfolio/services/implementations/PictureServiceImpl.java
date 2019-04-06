package com.portfolio.services.implementations;

import com.portfolio.models.Picture;
import com.portfolio.repositories.PictureRepository;
import com.portfolio.services.PictureService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

  private final PictureRepository repository;

  public PictureServiceImpl(PictureRepository repository) {
    this.repository = repository;
  }

  @Override
  public Iterable<Picture> getAllPictures() {
    return null;
  }

  @Override
  public Optional<Picture> getPictureById(Long id) {
    return Optional.empty();
  }

  @Override
  public Optional<Picture> addPicture(Picture picture) {
    return Optional.empty();
  }

  @Override
  public Optional<Picture> updatePicture(Picture picture) {
    return Optional.empty();
  }

  @Override
  public boolean deletePictureById(Long id) {
    return false;
  }

  @Override
  public boolean hidePicture(Long id) {
    return false;
  }
}
