package br.com.furb.controller;

import java.util.List;

import br.com.furb.enumeration.Role;
import br.com.furb.model.User;
import br.com.furb.model.dto.UserDTO;

public interface UserController {

    User find(Long id);

    List<UserDTO> findAll(Role role);

    boolean delete(Long id);

    boolean createNewUser(String name, Integer role, String password);

    boolean updateUserRole(Long id, Integer role);

    boolean updateUserCard(Long id, String card);

    boolean login(String name, String password);

    Role getUserRole(String name);

    boolean isAdmin(String name);

}
