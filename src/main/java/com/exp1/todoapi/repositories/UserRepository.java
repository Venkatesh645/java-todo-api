package com.exp1.todoapi.repositories;

import com.exp1.todoapi.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

  UserEntity findByEmail(String email);
}
