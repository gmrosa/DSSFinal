package br.com.furb.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.furb.controller.UserController;
import br.com.furb.cripto.Sha256;
import br.com.furb.enumeration.Role;
import br.com.furb.model.User;
import br.com.furb.repository.UserRepository;

@Service
@Transactional
public class UserControllerImpl implements UserController {

    @Autowired
    private UserRepository repository;

    private String getSecret(String salt, String password) {
        String secret = salt + password;
        return Sha256.getHash(secret);
    }

    @Override
    public User find(Long id) {
        return repository.find(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        repository.delete(id);

        return true;
    }

    @Override
    public boolean createNewUser(String name, Integer role, String password) {
        User user = new User();

        user.setName(name);
        try {
            user.setRole(Role.values()[role]);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        String salt = UUID.randomUUID().toString().replace("-", "");
        user.setSalt(salt);
        user.setPassword(getSecret(salt, password));

        repository.create(user);

        return true;
    }

    @Override
    public boolean updateUserRole(Long id, Integer role) {
        User user = find(id);
        user.setRole(Role.values()[role]);
        repository.update(user);

        return true;
    }

    @Override
    public boolean login(String name, String password) {
        User user = repository.findByName(name);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(getSecret(user.getSalt(), password));
    }

    @Override
    public Role getUserRole(String name) {
        return repository.getUserRole(name);
    }

    @Override
    public boolean isAdmin(String name) {
        return repository.getUserRole(name) == Role.ADMIN;
    }

}
