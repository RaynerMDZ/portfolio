package com.portfolio.controllers.cms;

import com.portfolio.Util.CustomException;
import com.portfolio.entities.Picture;
import com.portfolio.entities.Post;
import com.portfolio.services.PictureService;
import com.portfolio.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
  @GetMapping("/admin")
  public String getAdmin(Model model) {

    model.addAttribute("posts", postService.getAllPosts());

    return "cms/admin";
  }

//  /**
//   * When a post is clicked, the server shows a page to edit it.
//   * @param id
//   * @param model
//   * @return String
//   */
//  @GetMapping("/{id}/edit-post")
//  public String editPostForm(@PathVariable Long id, Model model) {
//
//    Post post = postService.getPostById(id);
//    List<String> images = new ArrayList<>();
//
//    System.out.println(post.getPictures().size());
//
//    int index = 0;
//    while (index < post.getPictures().size()) {
//      log.debug("Entering while loop, index: " + index);
//      byte[] bytes = new byte[post.getPictures().get(index).getPictureBytes().length];
//      log.debug("byte array created.");
//
//      int i = 0;
//      for (Byte wrappedByte : post.getPictures().get(index).getPictureBytes()) {
//        bytes[i++] = wrappedByte;
//      }
//
//      log.debug("byte array filled.");
//
//      String stringImage = Base64.encodeBase64String(bytes);
//      images.add(stringImage);
//      index++;
//
//      log.debug("converted to a string and saved.");
//
//    }
//
//    model.addAttribute("images", images);
//    model.addAttribute("post", post);
//
//    return "cms/edit-post";
//  }

  /**
   * When a post is clicked, the server shows a page to edit it.
   * @param id
   * @param model
   * @return String
   */
  @GetMapping("/{id}/edit-post")
  public String editPostForm(@PathVariable Long id, Model model) {

    model.addAttribute("post", postService.getPostById(id));
    return "cms/edit-post";
  }

  /**
   * Opens a new page with empty inputs to create a new post.
   * @return String
   */
  @GetMapping("/new-post")
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
  @PostMapping("/create-post")
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
  @PostMapping("/update-post")
  public String updatePost(@ModelAttribute("post") Post post, @RequestParam("file") MultipartFile file) {

    System.out.println(post.getTitle());

    Post savedPost = postService.updatePost(post);

    pictureService.savePicture(post.getId(), file);

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
  @DeleteMapping("/{id}/delete-post")
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
  @PutMapping("/{id}/hide-post")
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
  @PutMapping("/{id}/update-picture")
  public String updatePicture(@PathVariable Long id, @RequestBody Picture picture, Model model) {

    return "fragments/portfolio/{id}/post";
  }

  /**
   *
   * @param id
   * @return String
   */
  @DeleteMapping("/{id}/delete-picture")
  public String deletePicture(@PathVariable Long id) {

    boolean success = pictureService.deletePictureById(id);

    if (!success) {
      return "error/error-500";
    }
    return "redirects:/cms/edit-post";
  }

  /**
   *
   * @param id
   * @param model
   * @return String
   */
  @PutMapping("/{id}/hide-picture")
  public String hidePicture(@PathVariable Long id, Model model) {

    return "redirects:/cms/edit-post";
  }
}
