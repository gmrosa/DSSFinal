package br.com.furb.repository;

import java.util.List;

public interface GenericRepository<Entity> {

    void create(Entity entity);

    void update(Entity entity);

    void delete(Long id);

    Entity find(Long id);
    
    Entity findSpecial(String sql);

    List<Entity> findAll();

}
