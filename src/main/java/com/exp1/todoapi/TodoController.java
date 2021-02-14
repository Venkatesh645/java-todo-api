package com.exp1.todoapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
  public Iterable<Todo> list() {
    Iterable<Todo> todos = this.repository.findAll();
    System.out.println(todos);
    return todos;
  }

  @GetMapping("/{id}")
  public Todo show(@PathVariable(value = "id") long id) {
    Optional<Todo> TodoOpt = this.repository.findById(id);
    if (TodoOpt.isPresent()) {
      return TodoOpt.get();
    } else {
      throw new NotFoundException();
    }
  }

  @PostMapping(path = "", consumes = "application/json", produces = "application/json")
  public Todo create(@RequestBody Todo todo) {
    return this.repository.save(todo);
  }


}
