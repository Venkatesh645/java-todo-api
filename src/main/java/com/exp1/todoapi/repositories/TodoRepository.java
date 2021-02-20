package com.exp1.todoapi.repositories;

import com.exp1.todoapi.entities.TodoEntity;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<TodoEntity, Long> {
}
