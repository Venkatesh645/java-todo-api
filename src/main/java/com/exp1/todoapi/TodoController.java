package com.exp1.todoapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController {
  TodoRepository repository;

  @Autowired
  public TodoController(TodoRepository repository) {
    this.repository = repository;
  }

  @RequestMapping("")
  public Iterable<Todo> list() {
    Iterable<Todo> todos = this.repository.findAll();
    System.out.println(todos);
    return todos;
  }

  @RequestMapping("/{id}")
  public Object show(@PathVariable(value = "id") long id) {
    return this.repository.findById(id);
  }


}
