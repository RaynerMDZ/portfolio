package com.portfolio.entities;

import javax.persistence.*;
import java.io.Serializable;

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
}
