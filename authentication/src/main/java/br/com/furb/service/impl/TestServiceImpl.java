package br.com.furb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.furb.dao.TestDao;
import br.com.furb.model.Test;
import br.com.furb.service.TestService;

@Service
@Transactional
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao dao;

	@Override
	public void create(Test apiRequest) {
		dao.create(apiRequest);
	}

	@Override
	public void update(Test apiRequest) {
		dao.update(apiRequest);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public Test getById(Long id) {
		return dao.getById(id);
	}

}
