package com.portfolio.security.repositories;

import com.portfolio.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rayner MDZ
 */
@Repository
public interface UserRespository extends JpaRepository<User, Long> {
  User findByUsername(String username);
}
