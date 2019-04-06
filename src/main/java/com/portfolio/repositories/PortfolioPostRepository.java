package com.portfolio.repositories;

import com.portfolio.entities.PortfolioPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioPostRepository extends JpaRepository<PortfolioPostEntity, Long> {
}
