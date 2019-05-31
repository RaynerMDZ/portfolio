package com.portfolio.controllers;

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

  public indexController(PostService portfolioPostService) {
    this.portfolioPostService = portfolioPostService;
  }

  /**
   *
   * @return String
   */
  @GetMapping
  @RequestMapping({"", "/", "/index", "/index.html"})
  public String getIndex(Model model) {
    model.addAttribute("posts", portfolioPostService.getAllPosts());
    return "v2/index";
  }
}
