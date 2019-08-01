package com.portfolio.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Comment Entity that holds all database information.
 */
@Entity
@Table(name = "comments",
        uniqueConstraints = {@UniqueConstraint(columnNames = "id")},
        indexes = @Index(name = "id", columnList = "id")
)
public class Comment extends BaseEntity {

  @Size(min=2, max=15, message="Name must be between 2 and 15 characters")
  @Column(name = "name", unique = false, nullable = true)
  private String name;

  @Size(min=5, max=500, message="body must be at least 5 characters")
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
