package com.portfolio.dataloader;

import com.portfolio.entities.Post;
import com.portfolio.services.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  private final PostService postService;

  public DataLoader(PostService postService) {
    this.postService = postService;
  }

  @Override
  public void run(String... args) throws Exception {

    Post post = new Post();
    post.setTitle("Testtttt");
    post.setDescription("testttttttttttttttttttttttt");

    postService.createPost(post);

  }
}
