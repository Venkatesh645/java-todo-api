package com.exp1.todoapi.controllers;

import com.exp1.todoapi.dtos.AppResponseDTO;
import com.exp1.todoapi.dtos.ErrorDTO;
import com.exp1.todoapi.dtos.RegisterDTO;
import com.exp1.todoapi.entities.UserEntity;
import com.exp1.todoapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
  private UserRepository userRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public UsersController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/by-email")
  public AppResponseDTO findByEmail(@RequestBody String email) {
    AppResponseDTO appResponse = new AppResponseDTO();
    try {
      UserEntity user = userRepository.findByEmail(email);
      if (user != null) {
        appResponse.setResponse(user);
      }
      return appResponse;
    } catch (Exception error) {
      ErrorDTO errorObj = new ErrorDTO();
      errorObj.setMessage(error.getMessage());
      List errors = new ArrayList<ErrorDTO>();
      errors.add(errorObj);
      appResponse.setErrors(errors);
      return appResponse;
    }
  }

  @PostMapping("/register")
  public AppResponseDTO register(@RequestBody RegisterDTO reqBody) {
    AppResponseDTO appResponse = new AppResponseDTO();
    try {

      UserEntity userEntity = new UserEntity();
      userEntity.setEmail(reqBody.getEmail());

      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      userEntity.setPassword(reqBody.getPassword());
      UserEntity userResp = this.userRepository.save(userEntity);
      if (userResp != null) {
        appResponse.setResponse(userResp);
      }
      return appResponse;
    } catch (Exception error) {
      return appResponse;
    }
  }


}
