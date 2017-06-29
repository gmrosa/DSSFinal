package br.com.furb.repository;

import br.com.furb.enumeration.Role;
import br.com.furb.model.User;

public interface UserRepository extends GenericRepository<User> {
    
    User findByName(String name);
    
    Role getUserRole(String name);

}
