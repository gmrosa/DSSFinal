package br.com.furb.dao.impl;

import java.util.List;

import org.hibernate.Session;

import br.com.furb.dao.GenericDao;
import br.com.furb.dao.impl.Transaction.TransactionCallback;

public abstract class GenericDaoImpl<Entity> implements GenericDao<Entity> {

	public abstract Class<Entity> getClazz();

	@Override
	public void create(Entity entity) {
		Transaction.commit(new TransactionCallback() {

			@Override
			public Object accept(Session session) {
				session.save(entity);
				return null;
			}

		});
	}

	@Override
	public void update(Entity entity) {
		Transaction.commit(new TransactionCallback() {

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
			Transaction.commit(new TransactionCallback() {

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
		return (Entity) Transaction.open(new TransactionCallback() {

			@Override
			public Object accept(Session session) {
				return session.createQuery(String.format("from %s where id = %d", getClazz().getSimpleName(), id)).uniqueResult();
			}

		});
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Entity> findAll() {
		return (List<Entity>) Transaction.open(new TransactionCallback() {

			@Override
			public Object accept(Session session) {
				return session.createQuery(String.format("from %s", getClazz().getSimpleName())).list();
			}

		});
	}

}
