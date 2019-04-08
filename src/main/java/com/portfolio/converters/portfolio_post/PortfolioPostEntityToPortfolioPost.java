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
public class PortfolioPostEntityToPortfolioPost implements Converter<PostEntity, PortfolioPost> {

  @Synchronized
  @Nullable
  @Override
  public PortfolioPost convert(PostEntity entity) {
    if (entity == null) return null;

    final PortfolioPost post = new PortfolioPost();
    post.setId(entity.getId());
    post.setTitle(entity.getTitle());
    post.setDescription(entity.getDescription());
    post.setCreatedDate(entity.getCreatedDate());
    post.setModifiedDate(entity.getModifiedDate());

    return post;
  }
}
