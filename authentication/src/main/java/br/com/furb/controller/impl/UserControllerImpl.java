package br.com.furb.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.furb.controller.UserController;
import br.com.furb.cripto.MyBase64;
import br.com.furb.cripto.OneTimeXor;
import br.com.furb.cripto.Sha256;
import br.com.furb.enumeration.Role;
import br.com.furb.model.User;
import br.com.furb.model.converter.UserDTOConverter;
import br.com.furb.model.dto.UserDTO;
import br.com.furb.repository.UserRepository;

@Service
@Transactional
public class UserControllerImpl implements UserController {

    public static final String key = "12345ABCDE543210";

    @Autowired
    private UserRepository repository;

    private String getSecret(String salt, String password) {
        String secret = salt + password;
        return Sha256.getHash64(secret);
    }

    private void update(User user) {
        user.setEssence(Sha256.getHash(user.toString()));
        repository.update(user);
    }

    @Override
    public User find(Long id) {
        return repository.find(id);
    }

    @Override
    public List<UserDTO> findAll(Role role) {
        List<UserDTO> users = UserDTOConverter.toDTO(repository.findAll());

        OneTimeXor otx = new OneTimeXor(UserControllerImpl.key);
        users.forEach(dto -> {
            if (role == Role.EMPLOYEE) {
                dto.card = null;
            } else {
                if (dto.card != null) {
                    dto.card = new String(otx.decrypt(MyBase64.decode(dto.card)));
                }
            }
        });
        return users;
    }

    @Override
    public boolean delete(Long id) {
        if (id > 1) {
            repository.delete(id);
            return true;
        }
        return false;
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

        user = repository.findByName(name);
        update(user);

        return true;
    }

    @Override
    public boolean updateUserRole(Long id, Integer role) {
        User user = find(id);
        user.setRole(Role.values()[role]);
        update(user);

        return true;
    }

    @Override
    public boolean updateUserCard(Long id, String card) {
        User user = find(id);
        OneTimeXor otx = new OneTimeXor(key);
        user.setCard(new String(MyBase64.encode(otx.encrypt(card.getBytes()))));
        update(user);

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
