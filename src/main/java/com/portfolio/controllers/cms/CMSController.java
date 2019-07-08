package com.portfolio.controllers.cms;

import com.portfolio.Util.CustomException;
import com.portfolio.entities.Comment;
import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

/**
 *
 */
@Slf4j
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
   * Shows the admin page with all the posts.
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
   * When a post is clicked, the server shows a page to edit it.
   * @param id
   * @param model
   * @return String
   */
  @GetMapping
  @RequestMapping("/{id}/edit-post")
  public String editPostForm(@PathVariable Long id, Model model) {

    Post post = this.postService.getPostById(id);
    List<Comment> commentList = post.getComments();
    Collections.reverse(commentList);

    model.addAttribute("comments", commentList);
    model.addAttribute("post", postService.getPostById(id));
    return "cms/edit-post";
  }

  /**
   * Opens a new page with empty inputs to create a new post.
   * @return String
   */
  @GetMapping
  @RequestMapping("/new-post")
  public String newPostForm(Model model) {

    // Sends a new empty Post object to the view.
    model.addAttribute("post", new Post());

    return "cms/new-post";
  }

  /**
   *
   * @param post
   * @return String
   */
  @PostMapping
  @RequestMapping("/create-post")
  public String createPost(@ModelAttribute("post") Post post) {

    Post savedPost = postService.createPost(post);

    if (savedPost != null) {
      return "redirect:admin";
    }
    return "error/error";
  }

  /**
   *
   * @param post
   * @return String
   */
  @PostMapping
  @RequestMapping("/update-post")
  public String updatePost(@ModelAttribute("post") Post post, @RequestParam("file") MultipartFile file) {

    System.out.println(post.getTitle());

    Post savedPost = postService.updatePost(post);

    if (!file.isEmpty()) {
      pictureService.savePicture(post.getId(), file);
    }

    if (savedPost != null) {
      return "redirect:/cms/admin";
    }
    return "error/error-500";
  }

  /**
   *
   * @param id
   * @param model
   * @return String
   */
  @DeleteMapping
  @RequestMapping("/{id}/delete-post")
  public String deletePost(@PathVariable Long id, Model model) {

    try {
      boolean success = postService.deletePostById(id);
      if (!success) throw new CustomException("Could not delete the post.");
      return "redirect:/cms/admin";

    } catch (CustomException e) {
      System.out.println(e.getMessage());
      model.addAttribute("errorMessage", e.getMessage());
      return "error/error-500";
    }
  }

  /**
   *
   * @param id
   * @return String
   */
  @PutMapping
  @RequestMapping("/{id}/hide-post")
  public String hidePost(@PathVariable Long id) {

    postService.hidePost(id);
    return "redirect:/cms/admin";
  }

  /**
   *
   * @param id
   * @param picture
   * @param model
   * @return String
   */
  @PutMapping
  @RequestMapping("/{id}/update-picture")
  public String updatePicture(@PathVariable Long id, @RequestBody Picture picture, Model model) {

    return "fragments/portfolio/{id}/post";
  }

  /**
   *
   * @param id
   * @return String
   */
  @DeleteMapping
  @RequestMapping("/{id}/delete-picture")
  public String deletePicture(@PathVariable Long id) {

    Picture picture = pictureService.getPictureById(id);

    boolean success = pictureService.deletePictureById(id);

    if (!success) {
      return "error/error-500";
    }
    return "redirect:/cms/" + picture.getPost().getId() + "/edit-post";
  }

  /**
   *
   * @param id
   * @param model
   * @return String
   */
  @RequestMapping("/{id}/hide-picture")
  @PutMapping
  public String hidePicture(@PathVariable Long id, Model model) {

    return "redirects:/cms/edit-post";
  }
}
