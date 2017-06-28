package br.com.furb.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.furb.controller.UserController;
import br.com.furb.enumeration.Role;
import br.com.furb.model.User;
import br.com.furb.repository.UserRepository;

@Service
@Transactional
public class UserControllerImpl implements UserController {

    @Autowired
    private UserRepository repository;

    //    @Override
    //    public void delete(Long id) {
    //        repository.delete(id);
    //    }

    @Override
    public User find(Long id) {
        return repository.find(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public HttpStatus createNewUser(String name, Integer role, String password) {
//        repository.create(user);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus updateUserRole(Long id, Integer role) {
        User user = find(id);
        user.setRole(Role.values()[role]);
        repository.update(user);
        return HttpStatus.OK;
    }

}
