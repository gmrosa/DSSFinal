package com.mycuteblog.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mycuteblog.dao.ApiRequestDao;
import com.mycuteblog.model.ApiRequest;

@Repository
public class ApiRequestDaoImpl implements ApiRequestDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(ApiRequest apiRequest) {
		entityManager.persist(apiRequest);
	}

	@Override
	public void update(ApiRequest apiRequest) {
		entityManager.merge(apiRequest);
	}

	@Override
	public void delete(Long id) {
		ApiRequest apiRequest = getById(id);
		if (apiRequest != null) {
			entityManager.remove(apiRequest);
		}
	}

	@Override
	public ApiRequest getById(Long id) {
		return entityManager.find(ApiRequest.class, id);
	}

}
