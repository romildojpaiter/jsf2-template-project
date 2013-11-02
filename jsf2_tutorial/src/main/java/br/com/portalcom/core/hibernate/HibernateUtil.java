package br.com.portalcom.core.hibernate;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;

import br.com.portalcom.core.qualifier.HibernateInjectQualifaier;

@Named
@HibernateInjectQualifaier
public class HibernateUtil {

	@PersistenceContext
	private EntityManager entityManager;


	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public static void fechar(){
		// emf.close();
	}
	
	public Session getHibernateSession(){
		 HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
		 Session session = hem.getSession();
		 return session;
	}

}