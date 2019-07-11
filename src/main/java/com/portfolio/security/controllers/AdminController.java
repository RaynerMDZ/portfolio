package com.portfolio.security.controllers;

import com.portfolio.security.entities.User;
import com.portfolio.security.repositories.UserRespository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rayner MDZ
 */
@RestController
@RequestMapping("/secure/rest")
public class AdminController {

  private final UserRespository userRespository;
  private final BCryptPasswordEncoder passwordEncoder;

  public AdminController(UserRespository userRespository, BCryptPasswordEncoder passwordEncoder) {
    this.userRespository = userRespository;
    this.passwordEncoder = passwordEncoder;
  }

  //@PreAuthorize("hasAnyRole('ADMIN')")
  @PostMapping("/admin/add")
  public String addUserByAdmin(@RequestBody User user) {

    String password = user.getPassword();
    String encryptedPassword = passwordEncoder.encode(password);
    user.setPassword(encryptedPassword);

    userRespository.save(user);

    return "user added successfully";
  }

  @PreAuthorize("hasAnyRole('ADMIN')")
  @GetMapping("/admin/all")
  public String securedHello() {
    return "Secured Hello";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth != null){
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }

    return "redirect:v2/index";

  }
}
