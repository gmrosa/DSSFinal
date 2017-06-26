package br.com.furb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.furb.dao.UserDao;
import br.com.furb.model.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	@Override
	public void create(User user) {
		super.create(user);
	}

	@Override
	public void update(User user) {
		super.update(user);
	}

	@Override
	public User find(Long id) {
		return super.find(id);
	}

	@Override
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	public List<User> findAll() {
		return super.findAll();
	}

	@Override
	public Class<User> getClazz() {
		return User.class;
	}

}
