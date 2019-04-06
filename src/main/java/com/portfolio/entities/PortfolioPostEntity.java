package com.portfolio.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "portfolio_post", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class PortfolioPostEntity extends BaseEntity implements Serializable {

}
