package com.portfolio.controllers;

import com.portfolio.services.PictureService;
import com.portfolio.services.PortfolioPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class PortfolioPostController {

  private final PortfolioPostService portfolioPostService;
  private final PictureService pictureService;

  public PortfolioPostController(PortfolioPostService portfolioPostService, PictureService pictureService) {
    this.portfolioPostService = portfolioPostService;
    this.pictureService = pictureService;
  }

  @GetMapping
  @RequestMapping({"portfolio"})
  public String getAllPost(Model model) {
    model.addAttribute("posts", portfolioPostService.getAllPosts());
    model.addAttribute("picture", pictureService.findFirstPicture());
    return "fragments/portfolio/portfolio";
  }


  @GetMapping
  @RequestMapping("/post")
  public String getPostById(@PathVariable Long Id) {

    return null;
  }
}
