package br.com.furb.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Transaction {

	public static Object commit(TransactionCallback callback) {
		return open(new TransactionCallback() {

			@Override
			public Object accept(Session session) {
				session.beginTransaction();
				Object obj = callback.accept(session);
				session.getTransaction().commit();

				return obj;
			}
		});
	}

	public static Object open(TransactionCallback callback) {
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		return callback.accept(session);
	}

	interface TransactionCallback {

		Object accept(Session session);

	}

}
