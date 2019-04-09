package com.portfolio.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

  public static String UPLOAD_DIRECTORY =  System.getProperty("user.dir") + "/images";

  @RequestMapping("/image-test")
  public String loadPage() {
    return "test/image-uploader";
  }

  @RequestMapping("/upload")
  public String upload(Model model, @RequestParam("files")MultipartFile[] files) {
    StringBuilder fileNames = new StringBuilder();
    for (MultipartFile file : files) {
      Path fileNameAndPAth = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
      fileNames.append(file.getOriginalFilename());
      try {
        Files.write(fileNameAndPAth, file.getBytes());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    model.addAttribute("msg", "Successfully uploaded files " + fileNames.toString());
    return "test/uploadstatusview";
  }
}















