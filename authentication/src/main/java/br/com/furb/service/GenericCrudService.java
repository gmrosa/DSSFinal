package br.com.furb.service;

import java.util.List;

public interface GenericCrudService<Entity> {

	void create(Entity entity);

	void update(Entity entity);

	void delete(Long id);

	Entity find(Long id);

	List<Entity> findAll();

}
