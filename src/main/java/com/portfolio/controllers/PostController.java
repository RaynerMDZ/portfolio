package com.portfolio.controllers;

import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

/**
 *
 */
@Slf4j
@Controller
public class PostController {

  private final PostService portfolioPostService;
  private final PictureService pictureService;
  private final CommentController commentController;

  public PostController(PostService portfolioPostService,
                        PictureService pictureService,
                        CommentController commentController) {
    this.portfolioPostService = portfolioPostService;
    this.pictureService = pictureService;
    this.commentController = commentController;
  }
}
