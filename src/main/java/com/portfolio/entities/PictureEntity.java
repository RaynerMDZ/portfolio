package com.portfolio.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "pictures", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class PictureEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Lob
  @Column(name = "picture", unique = false, nullable = true)
  private Byte[] picture;


  private PortfolioPostEntity post;

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

  public PortfolioPostEntity getPost() {
    return post;
  }

  public void setPost(PortfolioPostEntity post) {
    this.post = post;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PictureEntity that = (PictureEntity) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
