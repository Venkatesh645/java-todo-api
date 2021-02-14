package com.exp1.todoapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoapiApplication {

  public static void main(String[] args) {
    SpringApplication.run(TodoapiApplication.class, args);
  }

  @Bean
  CommandLineRunner runner(TodoRepository repository) {
    return args -> {
      repository.save(new Todo("First todo"));
      repository.save(new Todo("Second todo"));
    };
  }


}
