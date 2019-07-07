package com.portfolio.security.entities;

import com.portfolio.entities.BaseEntity;

import javax.persistence.*;

/**
 * @author Rayner MDZ
 */
@Entity
@Table(name = "role", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Role extends BaseEntity {

  @Column(name = "role")
  private String role;

  public Role() {
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
