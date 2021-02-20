package com.exp1.todoapi.dtos;

import java.util.List;

public class AppResponseDTO {
  private Object response;
  private List<ErrorDTO> errors;
  private boolean success;

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

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }
}


