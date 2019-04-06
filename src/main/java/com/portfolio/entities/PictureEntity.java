package com.portfolio.entities;

import javax.persistence.*;

@Entity
@Table(name = "pictures", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class PictureEntity extends BaseEntity {

  @Lob
  @Column(name = "picture", unique = false, nullable = true)
  private Byte[] picture;

  @ManyToOne
  private PortfolioPostEntity post;

  public Byte[] getPicture() {
    return picture;
  }

  public void setPicture(Byte[] picture) {
    this.picture = picture;
  }

  public PortfolioPostEntity getPost() {
    return post;
  }

  public void setPost(PortfolioPostEntity post) {
    this.post = post;
  }
}
