package com.portfolio.controllers;

import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

  /**
   *
   * @param model
   * @return String
   */
  @GetMapping
  @RequestMapping({"/portfolio"})
  public String getAllPost(Model model) {

    model.addAttribute("posts", portfolioPostService.getAllPosts());
    model.addAttribute("service", pictureService);
    model.addAttribute("picture", pictureService.getPictureById(1L));
    return "fragments/portfolio/portfolio";
  }

  /**
   *
   * @param Id
   * @return String
   */
  @GetMapping
  @RequestMapping("/post")
  public String getPostById(@PathVariable Long Id) {

    return null;
  }
}
