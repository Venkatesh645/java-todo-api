package com.exp1.todoapi.repositories;

import com.exp1.todoapi.entities.TodoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<TodoEntity, Long> {
  List<TodoEntity> findAll();
}
