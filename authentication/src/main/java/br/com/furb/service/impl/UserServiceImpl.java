package br.com.furb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.furb.dao.UserDao;
import br.com.furb.model.User;
import br.com.furb.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Override
	public void create(User user) {
		dao.create(user);
	}

	@Override
	public void update(User user) {
		dao.update(user);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public User find(Long id) {
		return dao.find(id);
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

}
