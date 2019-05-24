package com.portfolio.controllers;

import com.portfolio.Util.Util;
import com.portfolio.services.CommentService;
import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
public class indexController {

  private final PostService portfolioPostService;
  private final PictureService pictureService;
  private final CommentService commentService;

  public indexController(PostService portfolioPostService, PictureService pictureService, CommentService commentService) {
    this.portfolioPostService = portfolioPostService;
    this.pictureService = pictureService;
    this.commentService = commentService;
  }

  /**
   *
   * @return String
   */
  @GetMapping
  @RequestMapping({"", "/", "/index", "/index.html"})
  public String getIndex(Model model) {
    model.addAttribute("posts", portfolioPostService.getAllPosts());
    return "index";
  }

  @GetMapping
  @RequestMapping({"V2","v2", "/V2", "/v2", "/V2/index", "/v2/index", "/V2/index.html", "/v2/index.html"})
  public String getIndexV2(Model model) {
    model.addAttribute("posts", portfolioPostService.getAllPosts());
    return "v2/index";
  }
}
