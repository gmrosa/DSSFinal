package br.com.furb.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.furb.model.ApiRequest;
import br.com.furb.repository.ApiRequestRepository;

@Repository
public class ApiRequestRepositoryImpl implements ApiRequestRepository {

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
		ApiRequest apiRequest = find(id);
		if (apiRequest != null) {
			entityManager.remove(apiRequest);
		}
	}

	@Override
	public ApiRequest find(Long id) {
		return entityManager.find(ApiRequest.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ApiRequest> findAll() {
		return entityManager.createNamedQuery("ApiRequest.findAll").getResultList();
	}

}
