package br.com.portalcom.core.dao.inter;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

public interface BaseDAO<T> extends Serializable{
	
	Session getSession();

	void flush();

	T merge(T obj) throws Exception;

	T saveOrUpdate(final T obj);

	Serializable insert(T obj) ;
	
	void update(final T obj) ;

    void delete(final T obj) ;
	
	List<T> findAll() ;
	
	List<T> findAll(String orderBy) ;

	List<T> findByCriteria(Criterion... criterion);

	List<T> findByCriteria(String orderField, Criterion... criterion);
	
	List<T> findByExample(final T example) ;
	
	List<T> findByExample(final T exampleInstance, String[] excludeProperty);

	List<T> findByField(String fieldOnTable, Object value);
	
	List<T> findByField(String[] fieldOnTable, Object[] value) ;
	
	List<T> findByFieldBetween(String fieldOnTable, Object valueInit, Object valueEnd) ;

	List<T> findByFieldInList(String fieldOnTable, List<Object> values);
	
	List<T> findByFieldLike(String fieldOnTable, String value) ;
	
	List<T> findByFieldLike(String[] fieldOnTable, Object[] value) ;
	
	List<T> findByFieldLikeOrdered(String fieldOnTable,String value, String orderBy) ;

	List<T> findByFieldOrdered(String fieldOnTable, Object value,String orderBy) ;

	T loadByField(String fieldName, Object value) ;

	T loadByField(String[] fieldOnTable, Object[] expression) ;
	
	T loadById(Serializable id) ;
}