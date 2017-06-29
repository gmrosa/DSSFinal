package br.com.furb.controller;

import java.util.List;

import br.com.furb.enumeration.Role;
import br.com.furb.model.User;

public interface UserController {

    User find(Long id);

    List<User> findAll();
    
    boolean delete(Long id);

    boolean createNewUser(String name, Integer role, String password);

    boolean updateUserRole(Long id, Integer role);

    boolean login(String name, String password);
    
    Role getUserRole(String name);
    
    boolean isAdmin(String name);

}
