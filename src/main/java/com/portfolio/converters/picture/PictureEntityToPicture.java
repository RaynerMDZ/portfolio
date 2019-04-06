package com.portfolio.converters.picture;

import com.portfolio.entities.PictureEntity;
import com.portfolio.models.Picture;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PictureEntityToPicture implements Converter<PictureEntity, Picture> {

  @Synchronized
  @Nullable
  @Override
  public Picture convert(PictureEntity pictureEntity) {
    if (pictureEntity == null) return null;

    final Picture picture = new Picture();
    picture.setPicture(pictureEntity.getPicture());

    if (pictureEntity.getPost() != null) {
      picture.setPostId(pictureEntity.getPost().getId());
    }

    return picture;
  }
}
