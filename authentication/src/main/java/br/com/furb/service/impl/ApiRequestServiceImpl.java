package br.com.furb.service.impl;

import br.com.furb.dao.ApiRequestDao;
import br.com.furb.model.ApiRequest;
import br.com.furb.service.ApiRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApiRequestServiceImpl implements ApiRequestService {

	@Autowired
	private ApiRequestDao dao;

	@Override
	public void create(ApiRequest apiRequest) {
		dao.create(apiRequest);
	}

	@Override
	public void update(ApiRequest apiRequest) {
		dao.update(apiRequest);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public ApiRequest getById(Long id) {
		return dao.getById(id);
	}

}
