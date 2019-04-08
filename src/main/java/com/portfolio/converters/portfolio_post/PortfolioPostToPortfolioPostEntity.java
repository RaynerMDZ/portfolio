package com.portfolio.converters.portfolio_post;

import com.portfolio.entities.PostEntity;
import com.portfolio.models.PortfolioPost;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PortfolioPostToPortfolioPostEntity implements Converter<PortfolioPost, PostEntity> {

  @Synchronized
  @Nullable
  @Override
  public PostEntity convert(PortfolioPost post) {
    if (post == null) return null;

    final PostEntity entity = new PostEntity();
    entity.setId(post.getId());
    entity.setTitle(post.getTitle());
    entity.setDescription(post.getDescription());
    entity.setCreatedDate(post.getCreatedDate());
    entity.setModifiedDate(post.getModifiedDate());

    return entity;
  }
}
