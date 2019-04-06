package com.portfolio.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "comments", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CommentEntity extends BaseEntity implements Serializable {

  @Column(name = "name", unique = false, nullable = false, length = 25)
  private String name;

  @Column(name = "body", unique = false, nullable = false, length = 500)
  private String body;

  @Column(name = "creation_date", unique = false, nullable = false)
  private LocalDate creationDate;

  private PortfolioPostEntity post;

}
