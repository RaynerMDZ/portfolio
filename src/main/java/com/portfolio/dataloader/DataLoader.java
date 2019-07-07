package com.portfolio.dataloader;

import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import com.portfolio.repositories.PictureRepository;
import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
public class DataLoader implements CommandLineRunner {

  private final PostService postService;
  private final PictureRepository pictureRepository;

  public DataLoader(PostService postService, PictureRepository pictureRepository) {
    this.postService = postService;
    this.pictureRepository = pictureRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    Post post1 = createPost("Coding All Day", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            "https://github.com/RaynerMDZ/CodingAllDay", "https://www.codingallday.com");

    Post post2 = createPost("Ecommerce", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            "https://github.com/RaynerMDZ/CodingAllDay", "https://www.codingallday.com");

    Post post3 = createPost("Project 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            "https://github.com/RaynerMDZ/CodingAllDay", "https://www.codingallday.com");

    Post post4 = createPost("Tacos", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            "https://github.com/RaynerMDZ/CodingAllDay", "https://www.codingallday.com");

    Post post5 = createPost("Burritos", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            "https://github.com/RaynerMDZ/CodingAllDay", "https://www.codingallday.com");

    addPicture("/images/ce3b832a5ae8430b8c855effa70e4453.jpg", post1);
    addPicture("/images/13dccf2dc9454ef5a4a7dfb98084384c.jpg", post2);
    addPicture("/images/ce3b832a5ae8430b8c855effa70e4453.jpg", post3);
    addPicture("/images/13dccf2dc9454ef5a4a7dfb98084384c.jpg", post4);
    addPicture("/images/ce3b832a5ae8430b8c855effa70e4453.jpg", post5);

  }

  private Post createPost(String title, String description, String github, String link) {
    Post post = new Post();
    post.setTitle(title);
    post.setDescription(description);
    post.setGitHub(github);
    post.setLink(link);
    post.setCreatedDate(new Date());
    post.setModifiedDate(new Date());
    post.setHidden(false);

    return postService.createPost(post);
  }

  private void addPicture(String pictureString, Post post) {

    Picture picture = new Picture();
    picture.setPictureString(pictureString);
    picture.setHidden(false);
    picture.setPost(post);

    pictureRepository.save(picture);
  }
}
