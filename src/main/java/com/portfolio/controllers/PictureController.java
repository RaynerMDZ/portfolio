package com.portfolio.controllers;

import com.portfolio.Util.Util;
import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Testing Purposes
 */
@Slf4j
@Controller
public class PictureController {

  private final PostService postService;
  private final PictureService pictureService;

  public PictureController(PostService postService, PictureService pictureService) {
    this.postService = postService;
    this.pictureService = pictureService;
  }

  @RequestMapping("/image-test")
  public String loadPage() {
    return "test/image-uploader";
  }

  @RequestMapping("/upload")
  public String upload(Model model, @RequestParam("files")MultipartFile[] files) {

    StringBuilder fileNames = new StringBuilder();

    // Mock
    Post post = postService.getPostById(2L);
    Picture picture = new Picture();


    for (MultipartFile file : files) {
      Path fileNameAndPAth = Paths.get(Util.UPLOAD_DIRECTORY, file.getOriginalFilename());
      fileNames.append(file.getOriginalFilename());

      System.out.println(fileNameAndPAth);

      try {
        Files.write(fileNameAndPAth, file.getBytes());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    model.addAttribute("message", "Successfully uploaded files " + fileNames.toString());
    // mock
    model.addAttribute("picture", postService.getPostById(post.getId()).getPictures());
    return "test/status";
  }

  public boolean rename(File file) {

    String URL = Util.IMAGE_URL + Util.generateString();
    File newFile = new File(URL);
    file.renameTo(newFile);
    return true;
  }
}















