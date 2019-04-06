package com.portfolio.converters.picture;

import com.portfolio.models.Picture;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PictureToPictureEntity implements Converter<Picture, com.portfolio.entities.PictureEntity> {

  @Synchronized
  @Nullable
  @Override
  public com.portfolio.entities.PictureEntity convert(Picture picture) {
    return null;
  }
}
