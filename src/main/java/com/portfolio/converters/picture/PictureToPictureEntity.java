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
public class PictureToPictureEntity implements Converter<Picture, PictureEntity> {

  @Synchronized
  @Nullable
  @Override
  public PictureEntity convert(Picture picture) {
    return null;
  }
}
