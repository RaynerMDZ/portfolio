package com.portfolio.entities;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "person",
        uniqueConstraints = {@UniqueConstraint(columnNames = "id")},
        indexes = @Index(name = "id", columnList = "id")
)
public class Person extends BaseEntity {

  @Column(name = "about_me", unique = false, nullable = true)
  private String aboutMe;

  @Column(name = "services", unique = false, nullable = true)
  private String services;

  @Column(name = "picture", unique = false, nullable = true)
  private String picture;

  @Column(name = "github", unique = false, nullable = true)
  private String github;

  @Column(name = "linkedin", unique = false, nullable = true)
  private String linkedIn;

  @Column(name = "twitter", unique = false, nullable = true)
  private String twitter;

  @Column(name = "youtube", unique = false, nullable = true)
  private String youtube;

  public String getAboutMe() {
    return aboutMe;
  }

  public void setAboutMe(String aboutMe) {
    this.aboutMe = aboutMe;
  }

  public String getServices() {
    return services;
  }

  public void setServices(String services) {
    this.services = services;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getGithub() {
    return github;
  }

  public void setGithub(String github) {
    this.github = github;
  }

  public String getLinkedIn() {
    return linkedIn;
  }

  public void setLinkedIn(String linkedIn) {
    this.linkedIn = linkedIn;
  }

  public String getTwitter() {
    return twitter;
  }

  public void setTwitter(String twitter) {
    this.twitter = twitter;
  }

  public String getYoutube() {
    return youtube;
  }

  public void setYoutube(String youtube) {
    this.youtube = youtube;
  }
}
