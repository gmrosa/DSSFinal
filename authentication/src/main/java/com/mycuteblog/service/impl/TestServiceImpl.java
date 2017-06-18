package com.mycuteblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycuteblog.dao.TestDao;
import com.mycuteblog.model.Test;
import com.mycuteblog.service.TestService;

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
