package com.portfolio.configuration.directory_config;

import com.portfolio.controllers.PictureController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class NewDirectory implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    new File(PictureController.UPLOAD_DIRECTORY).mkdir();
  }
}
