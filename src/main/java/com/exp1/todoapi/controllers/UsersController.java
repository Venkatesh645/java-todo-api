package com.exp1.todoapi.controllers;

import com.exp1.todoapi.dtos.AppResponseDTO;
import com.exp1.todoapi.dtos.ErrorDTO;
import com.exp1.todoapi.dtos.RegisterDTO;
import com.exp1.todoapi.entities.UserEntity;
import com.exp1.todoapi.exceptions.NotFoundException;
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

  @GetMapping("/{email}")
  public AppResponseDTO findByEmail(@RequestParam String email) {
    AppResponseDTO appResponse = new AppResponseDTO();
    try {
      UserEntity user = userRepository.findByEmail(email);
      if (user != null) {
        appResponse.setSuccess(true);
        appResponse.setResponse(user);
      } else {
        this.errorResponse(appResponse, "Record not found!!");
      }
    } catch (Exception error) {
      this.errorResponse(appResponse, error.getMessage());
    }
    return appResponse;
  }

  @PostMapping("/register")
  public AppResponseDTO register(@RequestBody RegisterDTO reqBody) {
    AppResponseDTO appResponse = new AppResponseDTO();
    try {

      UserEntity userEntity = new UserEntity();
      userEntity.setEmail(reqBody.getEmail());

      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      userEntity.setPassword(bCryptPasswordEncoder.encode(reqBody.getPassword()));
      UserEntity userResp = this.userRepository.save(userEntity);
      if (userResp != null) {
        appResponse.setSuccess(true);
        appResponse.setResponse(userResp);
      } else {
        this.errorResponse(appResponse, "Failed to save the user!");
      }
    } catch (Exception error) {
      this.errorResponse(appResponse, error.getMessage());
    }
    return appResponse;
  }


//  Private helper - errorResponse method
  private void errorResponse(AppResponseDTO appResponse, String message) {
    List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
    ErrorDTO errorObj = new ErrorDTO();
    appResponse.setSuccess(false);
    if (appResponse != null && message != null) {
      errorObj.setMessage(message);
      errors.add(errorObj);
      appResponse.setErrors(errors);
    }
  }


}
