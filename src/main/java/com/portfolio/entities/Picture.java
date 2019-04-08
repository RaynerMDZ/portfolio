package com.portfolio.entities;

import javax.persistence.*;

@Entity
@Table(name = "pictures", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Picture extends BaseEntity {

  @Lob
  @Column(name = "picture", unique = false, nullable = true)
  private Byte[] picture;

  @ManyToOne
  private Post post;

  public Byte[] getPicture() {
    return picture;
  }

  public void setPicture(Byte[] picture) {
    this.picture = picture;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }
}