package com.mycuteblog.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mycuteblog.dao.TestDao;
import com.mycuteblog.model.Test;

@Repository
public class TestDaoImpl implements TestDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Test apiRequest) {
		entityManager.persist(apiRequest);
	}

	@Override
	public void update(Test apiRequest) {
		entityManager.merge(apiRequest);
	}

	@Override
	public Test getById(Long id) {
		return entityManager.find(Test.class, id);
	}

	@Override
	public void delete(Long id) {
		Test test = getById(id);
		if (test != null) {
			entityManager.remove(test);
		}
	}

}
