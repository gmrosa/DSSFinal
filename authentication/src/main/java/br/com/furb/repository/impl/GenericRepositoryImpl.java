package br.com.furb.repository.impl;

import java.util.List;

import org.hibernate.Session;

import br.com.furb.repository.GenericRepository;
import br.com.furb.repository.impl.DBTransaction.TransactionCallback;

public abstract class GenericRepositoryImpl<Entity> implements GenericRepository<Entity> {

    public abstract Class<Entity> getClazz();

    @Override
    public void create(Entity entity) {
        DBTransaction.commit(new TransactionCallback() {

            @Override
            public Object accept(Session session) {
                session.save(entity);
                return null;
            }

        });
    }

    @Override
    public void update(Entity entity) {
        DBTransaction.commit(new TransactionCallback() {

            @Override
            public Object accept(Session session) {
                session.update(entity);
                return null;
            }

        });
    }

    @Override
    public void delete(Long id) {
        Entity entity = find(id);
        if (entity != null) {
            DBTransaction.commit(new TransactionCallback() {

                @Override
                public Object accept(Session session) {
                    session.delete(entity);

                    return null;
                }

            });
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Entity find(Long id) {
        return (Entity) DBTransaction.open(new TransactionCallback() {

            @Override
            public Object accept(Session session) {
                return session.createQuery(String.format("from %s where id = %d", getClazz().getSimpleName(), id)).uniqueResult();
            }

        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public Entity findSpecial(String sql) {
        return (Entity) DBTransaction.open(new TransactionCallback() {

            @Override
            public Object accept(Session session) {
                return session.createQuery(String.format("from %s where %s", getClazz().getSimpleName(), sql)).uniqueResult();
            }

        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Entity> findAll() {
        return (List<Entity>) DBTransaction.open(new TransactionCallback() {

            @Override
            public Object accept(Session session) {
                return session.createQuery(String.format("from %s", getClazz().getSimpleName())).list();
            }

        });
    }

}
