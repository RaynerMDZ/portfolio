package com.portfolio.models;

public class Picture {

  private Long id;
  private Byte[] picture;
  private Long postId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Byte[] getPicture() {
    return picture;
  }

  public void setPicture(Byte[] picture) {
    this.picture = picture;
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }
}
