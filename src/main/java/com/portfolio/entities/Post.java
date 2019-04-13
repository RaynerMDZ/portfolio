package com.portfolio.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.TemporalType.DATE;

/**
 *
 */
@Entity
@Table(name = "post", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Post extends BaseEntity {

  @Column(name = "title", unique = false, nullable = true)
  private String Title;

  @Column(name = "description", unique = false, nullable = true)
  private String description;

  @Temporal(DATE)
  @DateTimeFormat(pattern="dd-MMM-YYYY")
  @Column(name = "created_date", unique = false, nullable = true)
  private Date createdDate;

  @Temporal(DATE)
  @DateTimeFormat (pattern="dd-MMM-YYYY")
  @Column(name = "modified_date", unique = false, nullable = true)
  private Date modifiedDate;

  @Column(name = "hidden", unique = false, nullable = true)
  private Boolean hidden;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
  private List<Picture> pictures = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
  private List<Comment> comments = new ArrayList<>();

  public String getFirstPicture() {
    if (pictures.size() > 0) {
      return pictures.get(0).getPicture();
    }
    return null;
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

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(Date modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public Boolean isHidden() {
    return hidden;
  }

  public void setHidden(Boolean hidden) {
    this.hidden = hidden;
  }

  public List<Picture> getPictures() {
    return pictures;
  }

  public void setPictures(List<Picture> pictures) {
    this.pictures = pictures;
  }

  public List getComments() {
    return comments;
  }

  public void setComments(List comments) {
    this.comments = comments;
  }
}
