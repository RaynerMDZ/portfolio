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
public class CommentEntityToComment implements Converter<CommentEntity, Comment> {

  @Synchronized
  @Nullable
  @Override
  public Comment convert(CommentEntity entity) {
    if (entity == null) return null;

    final Comment comment = new Comment();
    comment.setId(comment.getId());

    if (entity.getPost() != null) {
      comment.setPostId(entity.getPost().getId());
    }

    comment.setBody(entity.getBody());
    comment.setCreationDate(entity.getCreationDate());

    return comment;
  }
}
