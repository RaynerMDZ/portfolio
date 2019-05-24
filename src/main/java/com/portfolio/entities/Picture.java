package com.portfolio.entities;

import javax.persistence.*;

/**
 * Picture Entity that holds all database information.
 */
@Entity
@Table(name = "pictures", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Picture extends BaseEntity {

  @Lob
  @Column(name = "picture", unique = false, nullable = true)
  private String picture;

  @Column(name = "hidden", unique = false, nullable = true)
  private Boolean hidden;

  @ManyToOne
  private Post post;

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public Boolean getHidden() {
    return hidden;
  }

  public void setHidden(Boolean hidden) {
    this.hidden = hidden;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }
}
