package com.portfolio.models;

public class Picture {

  private Byte[] picture;
  private PortfolioPost post;

  public Byte[] getPicture() {
    return picture;
  }

  public void setPicture(Byte[] picture) {
    this.picture = picture;
  }

  public PortfolioPost getPost() {
    return post;
  }

  public void setPost(PortfolioPost post) {
    this.post = post;
  }
}
