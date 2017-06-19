package br.com.furb.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.furb.dao.UserDao;
import br.com.furb.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(User apiRequest) {
		entityManager.persist(apiRequest);
	}

	@Override
	public void update(User apiRequest) {
		entityManager.merge(apiRequest);
	}

	@Override
	public User find(Long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public void delete(Long id) {
		User user = find(id);
		if (user != null) {
			entityManager.remove(user);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return entityManager.createQuery("from user").getResultList();
	}

}
