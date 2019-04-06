package com.portfolio.converters.portfolio_post;

import com.portfolio.entities.PortfolioPostEntity;
import com.portfolio.models.PortfolioPost;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PortfolioPostEntityToPortfolioPost implements Converter<PortfolioPostEntity, PortfolioPost> {

  @Synchronized
  @Nullable
  @Override
  public PortfolioPost convert(PortfolioPostEntity entity) {
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
