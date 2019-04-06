package com.portfolio.controllers;

import com.portfolio.services.CommentService;
import com.portfolio.services.PictureService;
import com.portfolio.services.PortfolioPostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

  private final PortfolioPostService portfolioPostService;
  private final PictureService pictureService;
  private final CommentService commentService;

  public indexController(PortfolioPostService portfolioPostService, PictureService pictureService, CommentService commentService) {
    this.portfolioPostService = portfolioPostService;
    this.pictureService = pictureService;
    this.commentService = commentService;
  }

  @RequestMapping({"", "/", "/index", "/index.html"})
  public String getIndex() {

    return "index";
  }
}
