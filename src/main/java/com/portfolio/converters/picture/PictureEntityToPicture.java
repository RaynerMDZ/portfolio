package com.portfolio.converters.picture;

import com.portfolio.models.Picture;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PictureEntityToPicture implements Converter<com.portfolio.entities.PictureEntity, Picture> {

  @Synchronized
  @Nullable
  @Override
  public Picture convert(com.portfolio.entities.PictureEntity pictureEntity) {
    return null;
  }
}
