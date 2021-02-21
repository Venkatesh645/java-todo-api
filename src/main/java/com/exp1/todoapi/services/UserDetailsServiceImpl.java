package com.exp1.todoapi.services;

import com.exp1.todoapi.entities.UserEntity;
import com.exp1.todoapi.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity applicationUser = userRepository.findByEmail(email);
    if (applicationUser == null) {
      throw new UsernameNotFoundException(email);
    }
    return new User(applicationUser.getEmail(), applicationUser.getPassword(), emptyList());
  }
}