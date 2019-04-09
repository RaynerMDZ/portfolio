package com.portfolio.entities;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "pictures", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Picture extends BaseEntity {

  @Column(name = "file_name", unique = false, nullable = true)
  private String fileName;

  @Column(name = "file_type", unique = false, nullable = true)
  private String fileType;

  @Lob
  @Column(name = "picture", unique = false, nullable = true)
  private Byte[] picture;

  @Column(name = "hidden", unique = false, nullable = true)
  private Boolean hidden;

  @ManyToOne
  private Post post;

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public Byte[] getPicture() {
    return picture;
  }

  public void setPicture(Byte[] picture) {
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
