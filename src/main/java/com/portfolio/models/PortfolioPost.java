package com.portfolio.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PortfolioPost {

  private Long id;
  private String Title;
  private String description;
  private LocalDate createdDate;
  private LocalDate modifiedDate;
  private Set<Picture> pictureEntities = new HashSet<>();
  private Set<Comment> commentEntities = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return Title;
  }

  public void setTitle(String title) {
    Title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public LocalDate getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(LocalDate modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public Set<Picture> getPictureEntities() {
    return pictureEntities;
  }

  public void setPictureEntities(Set<Picture> pictureEntities) {
    this.pictureEntities = pictureEntities;
  }

  public Set<Comment> getCommentEntities() {
    return commentEntities;
  }

  public void setCommentEntities(Set<Comment> commentEntities) {
    this.commentEntities = commentEntities;
  }
}
