package com.portfolio.security.repositories;

import com.portfolio.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rayner MDZ
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
