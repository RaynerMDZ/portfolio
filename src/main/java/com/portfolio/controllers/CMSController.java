package com.portfolio.controllers;

import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@Controller
@RequestMapping("/cms")
public class CMSController {

  private final PostService postService;
  private final PictureService pictureService;

  public CMSController(PostService postService, PictureService pictureService) {
    this.postService = postService;
    this.pictureService = pictureService;
  }

  /**
   *
   * @param model
   * @return String
   */
  @GetMapping
  @RequestMapping("/admin")
  public String getAdmin(Model model) {

    model.addAttribute("posts", postService.getAllPosts());

    return "cms/admin";
  }

  /**
   *
   * @param id
   * @param model
   * @return String
   */
  @GetMapping
  @RequestMapping("/{id}/edit-post")
  public String goToPost(@PathVariable Long id, Model model) {

    model.addAttribute("post", postService.getPostById(id));

    return "cms/edit-post";
  }

  /**
   *
   * @param post
   * @param model
   * @return String
   */
  @PostMapping
  @RequestMapping("/create-post")
  public String createPost(@RequestBody Post post, Model model) {

    return "fragments/portfolio/{id}/post";
  }

  /**
   *
   * @param post
   * @param model
   * @return String
   */
  @PutMapping
  @RequestMapping("/update-post")
  public String updatePost(@RequestBody Post post, Model model) {

    return "redirect:/cms/" + post.getId() + "/edit-post";
  }

  /**
   *
   * @param id
   * @param model
   * @return String
   */
  @DeleteMapping
  @RequestMapping("/delete-post/{id}")
  public String deletePost(@PathVariable Long id, Model model) {

    return "redirect:/cms/admin";
  }

  /**
   *
   * @param id
   * @param model
   * @return String
   */
  @PutMapping
  @RequestMapping("/hide-post/{id}")
  public String hidePost(@PathVariable Long id, Model model) {

    return "redirect:/cms/admin";
  }

  /**
   *
   * @param id
   * @param picture
   * @param model
   * @return String
   */
  @PostMapping
  @RequestMapping("/add-picture/{id}")
  public String addPicture(@PathVariable Long id, @RequestBody Picture picture, Model model) {

    return "fragments/portfolio/{id}/post";
  }

  /**
   *
   * @param id
   * @param picture
   * @param model
   * @return String
   */
  @PutMapping
  @RequestMapping("/update-picture/{id}")
  public String updatePicture(@PathVariable Long id, @RequestBody Picture picture, Model model) {

    return "fragments/portfolio/{id}/post";
  }

  /**
   *
   * @param id
   * @param model
   * @return String
   */
  @DeleteMapping
  @RequestMapping("/delete-picture/{id}")
  public String deletePicture(@PathVariable Long id, Model model) {

    return "redirects:/fragments/portfolio/{id}/post";
  }

  /**
   *
   * @param id
   * @param model
   * @return String
   */
  @PutMapping
  @RequestMapping("/hide-picture/{id}")
  public String hidePicture(@PathVariable Long id, Model model) {

    return "redirects:/fragments/portfolio/{id}/post";
  }
}
