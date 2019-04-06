package com.portfolio.converters.comment;

import com.portfolio.models.Comment;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommentEntityToComment implements Converter<com.portfolio.entities.CommentEntity, Comment> {

  @Synchronized
  @Nullable
  @Override
  public Comment convert(com.portfolio.entities.CommentEntity commentEntity) {
    return null;
  }
}
