package com.portfolio.entities;

import javax.persistence.*;

/**
 * Picture Entity that holds all database information.
 */
@Entity
@Table(name = "pictures", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Picture extends BaseEntity {

//  @Lob
//  @Column(name = "picture_bytes", unique = false, nullable = true)
//  @Basic(fetch = FetchType.EAGER)
//  private byte[] pictureBytes;

  @Lob
  @Column(name = "picture_string", unique = false, nullable = true)
  private String pictureString;

  @Column(name = "hidden", unique = false, nullable = true)
  private Boolean hidden;

  @ManyToOne
  private Post post;

//  public byte[] getPictureBytes() {
//    return pictureBytes;
//  }
//
//  public void setPictureBytes(byte[] pictureBytes) {
//    this.pictureBytes = pictureBytes;
//  }

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

  public String getPictureString() {
    return pictureString;
  }

  public void setPictureString(String pictureString) {
    this.pictureString = pictureString;
  }
}
