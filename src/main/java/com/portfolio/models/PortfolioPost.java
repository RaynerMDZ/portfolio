package com.portfolio.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PortfolioPost {

  private String Title;
  private String description;
  private LocalDate createdDate;
  private LocalDate modifiedDate;
  private Set<Picture> pictures = new HashSet<>();
  private Set<Comment> comments = new HashSet<>();
}
