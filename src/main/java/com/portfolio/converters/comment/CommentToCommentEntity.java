package com.portfolio.converters.comment;

import com.portfolio.entities.CommentEntity;
import com.portfolio.models.Comment;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommentToCommentEntity implements Converter<Comment, CommentEntity> {

  @Synchronized
  @Nullable
  @Override
  public CommentEntity convert(Comment comment) {
    return null;
  }
}
