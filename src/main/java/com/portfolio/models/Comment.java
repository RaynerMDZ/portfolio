package com.portfolio.models;

import java.time.LocalDate;

public class Comment {

  private String name;
  private String body;
  private LocalDate creationDate;
  private PortfolioPost post;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public PortfolioPost getPost() {
    return post;
  }

  public void setPost(PortfolioPost post) {
    this.post = post;
  }
}
