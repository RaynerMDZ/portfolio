package com.portfolio.dataloader;

import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  private final PostService postService;
  private final PictureService pictureService;

  public DataLoader(PostService postService, PictureService pictureService) {
    this.postService = postService;
    this.pictureService = pictureService;
  }

  @Override
  public void run(String... args) throws Exception {
  }
}
