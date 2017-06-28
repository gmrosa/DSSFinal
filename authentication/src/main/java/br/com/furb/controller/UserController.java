package br.com.furb.controller;

import java.util.List;

import org.springframework.http.HttpStatus;

import br.com.furb.model.User;

public interface UserController {

    User find(Long id);

    List<User> findAll();

    HttpStatus createNewUser(String name, Integer role, String password);

    HttpStatus updateUserRole(Long id, Integer role);

}
