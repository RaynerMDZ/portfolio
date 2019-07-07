package com.portfolio.security.services;

import com.portfolio.security.entities.User;
import com.portfolio.security.repositories.UserRespository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Rayner MDZ
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRespository userRespository;

  public CustomUserDetailsService(UserRespository userRespository) {
    this.userRespository = userRespository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRespository.findByUsername(username);
    CustomUserDetails userDetails = null;

    if (user != null) {
      userDetails = new CustomUserDetails();
      userDetails.setUser(user);
    } else {
      throw new UsernameNotFoundException("User with username: \"" + username + "\"" + " do not exist!");
    }
    return userDetails;
  }
}
