package com.portfolio.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rayner MDZ
 */
@RestController
@RequestMapping("/rest/auth")
public class ApplicationController {

  @GetMapping("/process")
  public String process() {
    return "processing...";
  }
}
