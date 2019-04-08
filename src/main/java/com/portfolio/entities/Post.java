package com.portfolio.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "portfolio_post", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Post extends BaseEntity {

  @Column(name = "title", unique = false, nullable = true)
  private String Title;

  @Column(name = "description", unique = false, nullable = true)
  private String description;

  @Column(name = "created_date", unique = false, nullable = true)
  private LocalDate createdDate;

  @Column(name = "modified_date", unique = false, nullable = true)
  private LocalDate modifiedDate;

  @Column(name = "hide", unique = false, nullable = true)
  private boolean hide;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
  private Set<Picture> pictures = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
  private Set<Comment> comments = new HashSet<>();

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

  public boolean isHide() {
    return hide;
  }

  public void setHide(boolean hide) {
    this.hide = hide;
  }

  public Set<Picture> getPictures() {
    return pictures;
  }

  public void setPictures(Set<Picture> pictures) {
    this.pictures = pictures;
  }

  public Set<Comment> getComments() {
    return comments;
  }

  public void setComments(Set<Comment> comments) {
    this.comments = comments;
  }
}
