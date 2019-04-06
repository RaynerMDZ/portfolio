package com.portfolio.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "portfolio", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class PortfolioEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;



}
