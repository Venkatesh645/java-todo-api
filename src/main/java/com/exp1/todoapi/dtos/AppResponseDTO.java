package com.exp1.todoapi.dtos;

import java.util.List;

public class AppResponseDTO {
  private Object response;
  private List<ErrorDTO> errors;

  public Object getResponse() {
    return response;
  }

  public void setResponse(Object response) {
    this.response = response;
  }

  public List<ErrorDTO> getErrors() {
    return errors;
  }

  public void setErrors(List<ErrorDTO> errors) {
    this.errors = errors;
  }
}


