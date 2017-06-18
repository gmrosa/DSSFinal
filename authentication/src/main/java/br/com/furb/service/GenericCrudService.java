package br.com.furb.service;

public interface GenericCrudService<Entity> {

	void create(Entity entity);

	void update(Entity entity);

	void delete(Long id);

	Entity getById(Long id);

}
