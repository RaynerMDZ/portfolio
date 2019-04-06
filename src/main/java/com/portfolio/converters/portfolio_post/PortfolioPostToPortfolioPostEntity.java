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
public class PortfolioPostToPortfolioPostEntity implements Converter<PortfolioPost, PortfolioPostEntity> {

  @Synchronized
  @Nullable
  @Override
  public PortfolioPostEntity convert(PortfolioPost post) {
    return null;
  }
}
