package com.exp1.todoapi.controllers;

import com.exp1.todoapi.dtos.AppResponseDTO;
import com.exp1.todoapi.dtos.ErrorDTO;
import com.exp1.todoapi.entities.TodoEntity;
import com.exp1.todoapi.exceptions.NotFoundException;
import com.exp1.todoapi.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/todos")
public class TodoController {
  TodoRepository repository;

  @Autowired
  public TodoController(TodoRepository repository) {
    this.repository = repository;
  }

  @GetMapping("")
  public AppResponseDTO list() {
    AppResponseDTO appResponse = new AppResponseDTO();
    try {
      List<TodoEntity> todos = this.repository.findAll();
      if (todos.isEmpty()) {
        this.errorResponse(appResponse, "No data found!!");
      } else {
        appResponse.setSuccess(true);
        appResponse.setResponse(todos);
      }

    } catch (Exception error) {
      this.errorResponse(appResponse, error.getMessage());
    }
    return appResponse;
  }

  @GetMapping("/{id}")
  public TodoEntity show(@PathVariable(value = "id") long id) {
    Optional<TodoEntity> TodoOpt = this.repository.findById(id);
    if (TodoOpt.isPresent()) {
      return TodoOpt.get();
    } else {
      throw new NotFoundException();
    }
  }

  @PostMapping("")
  public TodoEntity create(@RequestBody TodoEntity todo) {
    return this.repository.save(todo);
  }

  @PutMapping("/{id}")
  public TodoEntity update(@PathVariable(value = "id") long id, @RequestBody TodoEntity todo) {

    Optional<TodoEntity> todoOpt = this.repository.findById(id);

    if (todoOpt.isEmpty()) {
      throw new NotFoundException();
    }

    TodoEntity todoObj = todoOpt.get();
    todoObj.setTitle(todo.getTitle());
    return this.repository.save(todoObj);
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable(value = "id") long id) {
    Optional<TodoEntity> todoOpt = this.repository.findById(id);

    if (todoOpt.isEmpty()) {
      throw new NotFoundException();
    }
    this.repository.deleteById(id);
    return "Deleted todo successfully";
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
