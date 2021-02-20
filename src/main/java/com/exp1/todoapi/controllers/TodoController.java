package com.exp1.todoapi.controllers;

import com.exp1.todoapi.entities.TodoEntity;
import com.exp1.todoapi.exceptions.NotFoundException;
import com.exp1.todoapi.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
  public Iterable<TodoEntity> list() {
    Iterable<TodoEntity> todos = this.repository.findAll();
    System.out.println(todos);
    return todos;
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
  public TodoEntity update(@PathVariable(value="id") long id, @RequestBody TodoEntity todo) {

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


}
