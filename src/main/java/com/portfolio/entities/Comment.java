package com.portfolio.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Comment Entity that holds all database information.
 */
@Entity
@Table(name = "comments", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Comment extends BaseEntity {

  @Column(name = "name", unique = false, nullable = true, length = 25)
  private String name;

  @Column(name = "body", unique = false, nullable = true, length = 500)
  private String body;

  @Column(name = "creation_date", unique = false, nullable = true)
  private Date creationDate;

  @ManyToOne
  private Post post;

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

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }
}
