package br.desafio.util;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory factory;

	private static final String PERSISTENCE_UNIT_NAME = "sicoeDS";
	private static ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>();

	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return factory;
	}

	public static EntityManager getEntityManager() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		EntityManager manager = threadLocal.get();
		if (manager == null) {
			manager = factory.createEntityManager();
			threadLocal.set(manager);
		}
		return manager;
	}
	
	public static void startTransaction(EntityManager entityManager) {
		entityManager.getTransaction().begin();
	}
	
	public static void finishTransacton(EntityManager entityManager) {
		if (entityManager.isOpen()) {
			EntityTransaction tx = entityManager.getTransaction();
			if (tx.isActive()) {
				entityManager.getTransaction().commit();
			}
			entityManager.close();
		}
	}

	public static void transactionFailed(EntityManager entityManager) {
		if (entityManager.isOpen()) {
			EntityTransaction tx = entityManager.getTransaction();

			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
	}

	public static void closeEntityManager() {
		EntityManager entityManager = threadLocal.get();
		if (entityManager != null) {
			entityManager.close();
			threadLocal.set(null);
		}
	}

	public static void closeEntityManagerFactory() {
		closeEntityManager();
		factory.close();
	}
}
