package com.portfolio.controllers;

import com.portfolio.services.PictureService;
import com.portfolio.services.PortfolioPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/cms")
public class CMSController {

  private final PortfolioPostService postService;
  private final PictureService pictureService;

  public CMSController(PortfolioPostService postService, PictureService pictureService) {
    this.postService = postService;
    this.pictureService = pictureService;
  }
}
