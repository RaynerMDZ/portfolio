package com.portfolio.dataloader;

import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

//    Post post = new Post();
//    post.setTitle("Testtttt");
//    post.setDescription("testttttttttttttttttttttttt");

    //Picture picture = pictureService.uploadPicture(post.getId(), saveImageToDb());
    //post.getPictures().add(picture);

//    postService.createPost(post);

  }

  private MultipartFile saveImageToDb() {

    File file = new File("static/img/mock_img/mario.jpg");

    FileInputStream input = null;
    try {
      input = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      if (input != null) {
        MultipartFile multipartFile = new MockMultipartFile("fileItem",
                file.getName(), "image/png", IOUtils.toByteArray(input));
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return null;
  }
}
