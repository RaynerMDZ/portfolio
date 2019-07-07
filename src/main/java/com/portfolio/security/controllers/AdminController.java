package com.portfolio.security.controllers;

import com.portfolio.security.entities.User;
import com.portfolio.security.repositories.UserRespository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
}
