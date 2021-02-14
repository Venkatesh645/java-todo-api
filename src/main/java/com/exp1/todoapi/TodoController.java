package com.exp1.todoapi;

import com.sun.istack.NotNull;
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

  @PostMapping("")
  public Todo create(@RequestBody Todo todo) {
    return this.repository.save(todo);
  }

  @PutMapping("/{id}")
  public Todo update(@PathVariable(value="id") long id, @RequestBody Todo todo) {

    Optional<Todo> todoOpt = this.repository.findById(id);

    if (todoOpt.isEmpty()) {
      throw new NotFoundException();
    }

    Todo todoObj = todoOpt.get();
    todoObj.setTitle(todo.getTitle());
    return this.repository.save(todoObj);
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable(value = "id") long id) {
    Optional<Todo> todoOpt = this.repository.findById(id);

    if (todoOpt.isEmpty()) {
      throw new NotFoundException();
    }
    this.repository.deleteById(id);
    return "Deleted todo successfully";
  }


}
