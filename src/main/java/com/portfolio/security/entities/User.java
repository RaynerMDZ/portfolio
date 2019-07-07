package com.portfolio.security.entities;

import com.portfolio.entities.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rayner MDZ
 */
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class User extends BaseEntity {

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;


  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
          name = "user_role",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
