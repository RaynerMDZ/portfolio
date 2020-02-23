package com.portfolio.controllers;

import com.portfolio.entities.Post;
import com.portfolio.services.PostService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 */
@Controller
public class indexController {

  private final String PROFILE_PIC = "static/v4/images/profile.png";
  private final PostService portfolioPostService;

  public indexController(PostService portfolioPostService) {
    this.portfolioPostService = portfolioPostService;
  }

  /**
   *
   * @return String
   */
  @RequestMapping(
          value = {"", "/", "/index", "/index.html"},
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public String getIndex(Model model) {

    ArrayList<Post> posts = (ArrayList<Post>) portfolioPostService.getAllPosts();
    Collections.reverse(posts);

    final Post firstOne = posts.get(0);

    posts.remove(0);

    model.addAttribute("posts", posts);
    model.addAttribute("firstPost", firstOne);
    return "v4/index";
  }
}
