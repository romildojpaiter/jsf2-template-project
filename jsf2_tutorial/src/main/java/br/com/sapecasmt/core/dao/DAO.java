package br.com.sapecasmt.core.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.Order;
import javax.validation.ConstraintViolationException;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.sapecasmt.core.dao.inter.IDAO;
import br.com.sapecasmt.core.hibernate.HibernateUtil;

public class DAO<T> extends HibernateUtil implements IDAO<T> {

	/*public DAO<T> addParamsEq(String key, Object value) {
		paramsEq.put(key, value);
		return this;
	}

	public void save(Collection<T> ts) {
		int x = 0;
		doBeginTransaction();
		try {
			for (T t : ts) {
				try {
					getCurrentSession().saveOrUpdate(t);
				} catch (NonUniqueObjectException e) {
					getCurrentSession().merge(t);
				}
				if (x >= 10 && x % 10 == 0) {
					doFlush();
				}
				x++;
			}
			doCommit();
			doFlush();
		} catch (Exception e) {
			e.printStackTrace();
			doRollback();
		} finally {
			clearHibernateCache();
		}

	}

	public DAO<T> addParamsLike(String key, String value) {
		paramsLike.put(key, value);
		return this;
	}

	public DAO<T> clearParamsEq() {
		paramsEq.clear();
		return this;
	}

	public IDAO<T> clearAllParams() {
		paramsEq.clear();
		paramsLike.clear();
		paramsIsNotNull.clear();
		paramsIsNull.clear();
		return this;
	}

	public DAO<T> clearParamsLike() {
		paramsLike.clear();
		return this;
	}

	public T getWithParams(Class<T> clas) {
		Criteria query = getCurrentSession().createCriteria(clas);
		query.setCacheable(true);
		for (String field : paramsEq.keySet())
			query.add(Restrictions.eq(field, paramsEq.get(field)));

		for (String field : paramsLike.keySet())
			query.add(Restrictions.like(field, paramsLike.get(field),
					MatchMode.ANYWHERE));

		return (T) query.uniqueResult();
	}

	public T getEvictingCache(Class<T> clas, Integer id) {
		doBeginTransaction();
		T t = (T) getCurrentSession().load(clas, id);
		getCurrentSession().evict(t);
		doCommit();
		return t;
	}

	public T getEvictingCache(Class<T> clas, String campo, Object valor) {
		T t = (T) getCurrentSession().createCriteria(clas)
				.add(Restrictions.eq(campo, valor)).uniqueResult();
		getCurrentSession().evict(t);
		return t;
	}

	public T get(Class<T> clas, Integer id) {
		doBeginTransaction();
		T t = (T) getCurrentSession().load(clas, id);
		doCommit();
		return t;
	}

	public List<T> list(Class<T> clas) {
		return getCurrentSession().createCriteria(clas).list();
	}

	public void save(T t) throws ConstraintViolationException,
			UnsatisfiedDependencyException, Exception {
		try {
			if (!getCurrentSession().getTransaction().isActive()) {
				throw new UnsatisfiedDependencyException(
						"INICIALIZE UMA TRANSAÇÃO");
			}
			getCurrentSession().saveOrUpdate(t);
		} catch (NonUniqueObjectException e) {
			getCurrentSession().merge(t);
		} catch (ConstraintViolationException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	public T get(Class<T> clas, String campo, Object valor) {
		return (T) getCurrentSession().createCriteria(clas)
				.add(Restrictions.eq(campo, valor)).uniqueResult();
	}

	public List<T> search(Class<T> clas, String orderField) {
		return searchWithCache(clas, null, orderField);
	}

	public List<T> searchWithCache(Class<T> clas, CacheMode cacheMode,
			String orderField) {
		try {
			Criteria query = getCurrentSession().createCriteria(clas);
			if (cacheMode != null) {
				query.setCacheable(true);
				query.setCacheMode(cacheMode);
			} else {
				query.setCacheable(false);
			}
			System.out.println("[ParamsEQ] " + paramsEq);
			for (String key : paramsEq.keySet())
				query.add(Restrictions.eq(key, paramsEq.get(key)));

			for (String key : paramsLike.keySet())
				query.add(Restrictions.like(key, paramsLike.get(key),
						MatchMode.ANYWHERE));

			for (String fieldNull : paramsIsNull)
				query.add(Restrictions.isNull(fieldNull));

			for (String fieldNotNull : paramsIsNotNull)
				query.add(Restrictions.isNotNull(fieldNotNull));

			query.addOrder(Order.asc(orderField));
			// query.setMaxResults(1000) ;

			List<T> returnn = query.list();
			return returnn;
		} finally {
			clearAllParams();
		}

	}

	public IDAO<T> clearParamsIsNotNull() {
		paramsIsNull.clear();
		return this;
	}

	public IDAO<T> clearParamsIsNull() {
		paramsIsNotNull.clear();
		return this;
	}

	public IDAO<T> addParamsNotNull(String field) {
		paramsIsNotNull.add(field);
		return this;
	}

	public IDAO<T> addParamsNull(String field) {
		paramsIsNull.add(field);
		return this;
	}*/
}