package com.portfolio.controllers;

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

  private final PortfolioPostService service;

  public PortfolioPostController(PortfolioPostService service) {
    this.service = service;
  }

  @GetMapping
  @RequestMapping({"portfolio/portfolio"})
  public String getAllPost(Model model) {
    model.addAttribute("posts", service.getAllPosts());

    return "portfolio/portfolio";
  }


  @GetMapping
  @RequestMapping("/")
  public String getPostById(@PathVariable Long Id) {

    return null;
  }
}
