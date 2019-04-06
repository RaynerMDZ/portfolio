package com.portfolio.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comments", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CommentEntity extends BaseEntity {

  @Column(name = "name", unique = false, nullable = true, length = 25)
  private String name;

  @Column(name = "body", unique = false, nullable = true, length = 500)
  private String body;

  @Column(name = "creation_date", unique = false, nullable = true)
  private LocalDate creationDate;

  @ManyToOne
  private PortfolioPostEntity post;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public PortfolioPostEntity getPost() {
    return post;
  }

  public void setPost(PortfolioPostEntity post) {
    this.post = post;
  }
}
