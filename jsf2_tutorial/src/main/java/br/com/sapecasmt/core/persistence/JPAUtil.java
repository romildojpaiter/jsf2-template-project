package br.com.sapecasmt.core.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class JPAUtil {

	@PersistenceContext
	private EntityManager entityContext;

	public void incluir(Object obj) {
		entityContext.getTransaction().begin();
		entityContext.persist(obj);
		entityContext.getTransaction().commit();
	}

	public void excluir(Object obj) {
		entityContext.getTransaction().begin();
		entityContext.remove(obj);
		entityContext.getTransaction().commit();
	}

	public void alterar(Object obj) {
		entityContext.getTransaction().begin();
		entityContext.merge(obj);
		entityContext.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public <T> T ler(Class<T> clazz, Object codigo) {
		Object obj = entityContext.find(clazz, codigo);
		return (T) obj;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> listarTodos(Class<T> clazz) {
		Query q = entityContext.createQuery("from " + clazz.getName());
		List<T> l = q.getResultList();

		return l;
	}

	@SuppressWarnings("rawtypes")
	public List listarNamedQuery(String namedQuery) {
		Query q = entityContext.createNamedQuery(namedQuery);
		List l = q.getResultList();

		return l;
	}

	/*
	 * private static final String PERSISTENCE_UNIT_NAME = "default";
	 * 
	 * private static ThreadLocal<EntityManager> manager = new
	 * ThreadLocal<EntityManager>();
	 * 
	 * private static EntityManagerFactory factory;
	 * 
	 * private JPAUtil() { }
	 * 
	 * public static boolean isEntityManagerOpen() { return
	 * JPAUtil.manager.get() != null && JPAUtil.manager.get().isOpen(); }
	 * 
	 * public static EntityManager getEntityManager() { if (JPAUtil.factory ==
	 * null) { JPAUtil.factory = Persistence
	 * .createEntityManagerFactory(PERSISTENCE_UNIT_NAME); } EntityManager entityContext =
	 * JPAUtil.manager.get(); if (entityContext == null || !entityContext.isOpen()) { entityContext =
	 * JPAUtil.factory.createEntityManager(); JPAUtil.manager.set(entityContext); } return
	 * entityContext; }
	 * 
	 * public static void evictCache(EntityManager entityContext, String region) {
	 * ((Session) entityContext.getDelegate()).getSessionFactory().getCache()
	 * .evictQueryRegion(region); }
	 * 
	 * public static void closeEntityManager() { EntityManager entityContext =
	 * JPAUtil.manager.get(); if (entityContext != null) { EntityTransaction tx =
	 * entityContext.getTransaction(); if (tx.isActive()) { tx.commit(); } entityContext.close();
	 * JPAUtil.manager.set(null); } }
	 * 
	 * public static void closeEntityManagerFactory() { closeEntityManager();
	 * JPAUtil.factory.close(); }
	 */
}