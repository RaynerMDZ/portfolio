package com.portfolio.repositories;

import com.portfolio.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioPostRepository extends JpaRepository<PostEntity, Long> {
}
