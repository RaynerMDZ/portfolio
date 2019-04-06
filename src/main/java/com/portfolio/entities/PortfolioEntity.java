package com.portfolio.entities;

import javax.persistence.*;

@Entity
@Table(name = "portfolio", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class PortfolioEntity extends BaseEntity {



}
