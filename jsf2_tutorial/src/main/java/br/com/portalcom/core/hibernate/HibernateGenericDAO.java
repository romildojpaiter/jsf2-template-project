package br.com.portalcom.core.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.portalcom.core.dao.inter.BaseDAO;
import br.com.portalcom.core.interceptor.InterceptorLog;
import br.com.portalcom.core.qualifier.HibernateInjectQualifier;

@HibernateInjectQualifier
public class HibernateGenericDAO<T> implements BaseDAO<T>  {
	 
	private static final long serialVersionUID = 1L;

	protected Class<T> objClass = null;
	
	@Inject
	private Logger log;
	
	@Inject
	private HibernateUtil hibernateUtil;
	
	protected Session session;
	
	public static final String DAO_FIRST_RESULT_PARAM = "DAO_FIRST_RESULT_PARAM";
	public static final String DAO_MAX_RESULTS_PARAM = "DAO_MAX_RESULTS_PARAM"; 
 
	@SuppressWarnings("unchecked")
	public HibernateGenericDAO(){
		Type superclass = getClass().getGenericSuperclass();
		if(superclass instanceof ParameterizedType){
			ParameterizedType parameterizedType = (ParameterizedType)superclass;
			if(parameterizedType.getActualTypeArguments().length > 0){
				objClass = (Class<T>)parameterizedType.getActualTypeArguments()[0];
			}
		}
	}

	public HibernateGenericDAO(Class<T> objKlass) {
		this.setObjectClass(objKlass);
	}
	
	
	public void delete(final T obj)  {
		getSession().delete(obj);
		getSession().flush();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll()  {
		Criteria criteria = getSession().createCriteria(this.objClass);
		final List<T> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(String orderBy)  {
		Criteria criteria = getSession().createCriteria(this.objClass);
		criteria.addOrder(Order.asc(orderBy));
		final List<T> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
    public List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = getSession().createCriteria(this.objClass);
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
   }

	
	@SuppressWarnings("unchecked")
    public List<T> findByCriteria(String orderField, Criterion... criterion) {
        Criteria crit = getSession().createCriteria(this.objClass);
        for (Criterion c : criterion) {
            crit.add(c);
        }
        crit.addOrder(Order.asc(orderField));
        return crit.list();
   }

	
	public List<T> findByExample(final T example)  {
		return this.findByCriteria(Example.create(example).enableLike(MatchMode.ANYWHERE).ignoreCase());
	}

	
	public List<T> findByExample(final T exampleInstance, String[] excludeProperty) {
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		return this.findByCriteria(example);
	}

	public List<T> findByField(String fieldOnTable, Object value){
		return this.findByCriteria(Restrictions.eq(fieldOnTable, value).ignoreCase());
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByField(String[] fieldOnTable, Object[] value)  {
		
		Criteria c = getSession().createCriteria(this.objClass);

		for (int i = 0; i < fieldOnTable.length; i++) {

			if (value[i] instanceof String) {
				c.add(Restrictions.eq(fieldOnTable[i], String.valueOf(value[i])));
			} 
			else if (value[i] instanceof Date) {
				c.add(Restrictions.eq(fieldOnTable[i], value[i]));
			} 
			else {
				c.add(Restrictions.eq(fieldOnTable[i], value[i]));
			}
		}
		return c.list();
	}

	public List<T> findByFieldBetween(String field, Object valueInit, Object valueEnd)  {
		return this.findByCriteria(Restrictions.between(field, valueInit, valueEnd));
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByFieldInList(String field, List<Object> values) {
		Criteria criteria = getSession().createCriteria(this.objClass);
		criteria.add(Restrictions.in(field, values));
		return criteria.list();
	}
	
	public List<T> findByFieldLike(String fieldOnTable, String value)  {
		return this.findByCriteria(Restrictions.like(fieldOnTable, value, MatchMode.ANYWHERE).ignoreCase());
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByFieldLike(String[] fieldOnTable, Object[] value)  {
		
		Criteria criteira = getSession().createCriteria(this.objClass);

		for (int i = 0; i < fieldOnTable.length; i++) {

			if (value[i] instanceof String) {
				criteira.add(Restrictions.like(fieldOnTable[i], String.valueOf(value[i]), MatchMode.ANYWHERE).ignoreCase());
			} 
			else if (value[i] instanceof Date) {
				criteira.add(Restrictions.eq(fieldOnTable[i], value[i]));
			} 
			else {
				criteira.add(Restrictions.like(fieldOnTable[i], value[i]));
			}
		}
		return criteira.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByFieldLikeOrdered(String fieldOnTable,String value, String orderBy)  {
		Criteria criteria = getSession().createCriteria(this.objClass).add(Restrictions.like(fieldOnTable, value, MatchMode.ANYWHERE).ignoreCase());
		criteria.addOrder(Order.asc(orderBy));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByFieldOrdered(String fieldOnTable, Object value,String orderBy)  {
		Criteria criteria = getSession().createCriteria(this.objClass).add(Restrictions.eq(fieldOnTable, value));
		criteria.addOrder(Order.asc(orderBy));
		return criteria.list();
	}

	public void flush(){
		getSession().flush();
	}

	public Session getSession() {
		if(this.session == null){
			// this.session = (Session) hibernateUtil.getEntityManager().getDelegate();
			this.session = hibernateUtil.getHibernateSession(); 
		}
		return this.session;
	}


	public Serializable insert(T obj)  {
		showValidationErrors(obj);
		Serializable idGerado = getSession().save(obj);
		getSession().flush();
		return idGerado;
	}

	
	@SuppressWarnings("unchecked")
	public T loadByField(String fieldName, Object value)  {
		Criteria criteria = session.createCriteria(this.objClass).add(Restrictions.eq(fieldName, value));
		Object obj = criteria.uniqueResult();
		return (obj != null) ? (T) obj : null;
	}
	
	@SuppressWarnings("unchecked")
	public T loadByField(String[] fieldOnTable, Object[] expression)  {
		
		Criteria c = session.createCriteria(this.objClass);

		for (int i = 0; i < fieldOnTable.length; i++) {

			if (expression[i] instanceof String) {
				c.add(Restrictions.eq(fieldOnTable[i], String.valueOf(expression[i])));
			} 
			else if (expression[i] instanceof Date) {
				c.add(Restrictions.eq(fieldOnTable[i], expression[i]));
			} 
			else {
				c.add(Restrictions.eq(fieldOnTable[i], expression[i]));
			}
		}
		return (T) c.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public T loadById(Serializable id)  {
		return (T) session.load(this.objClass.getName(), id);
	}
	@SuppressWarnings("unchecked")
	public T merge(T obj)  {
		showValidationErrors(obj);
		return (T) session.merge(obj);
	}

	public T saveOrUpdate(final T obj) {
		showValidationErrors(obj);
		getSession().saveOrUpdate(obj);
		getSession().flush();
		return obj;
	}

    public void setObjectClass(Class<T> objectKlass) {
		this.objClass = objectKlass;
	}
    
    public Class<T> getObjectClass(){
    	return this.objClass;
    }
    
    public void setSession(Session session) {
		this.session = session;
	}
    
    @InterceptorLog
    private void showValidationErrors(T obj) {
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    	Validator validator = factory.getValidator();
    	
    	Set<ConstraintViolation<T>> constraintViolations = validator.validate( obj );
    	List<FacesMessage> listaMensagens = new ArrayList<FacesMessage>();
    	
    	for (ConstraintViolation<T> constraintViolation : constraintViolations) {
    		FacesMessage mensagem = new FacesMessage("No email value!", "Email Validation Error");
    		mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
    		listaMensagens.add(mensagem);
    		
    		log.warn("Entity: [" + constraintViolation.getRootBean().getClass().getCanonicalName() + 
					"] Value: [" + String.valueOf(constraintViolation.getInvalidValue()) + "] Msg: [" +constraintViolation.getMessage() +"]");
		}
    	
    	if(listaMensagens.size() > 0){
    		FacesContext context = FacesContext.getCurrentInstance();
    		context.getExternalContext().getSessionMap().put("MULTI_PAGE_MESSAGES_SUPPORT", listaMensagens);
    		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
    	    // session.setAttribute("MULTI_PAGE_MESSAGES_SUPPORT", listaMensagens);
    	}
    }

    /*private void showValidationErrors(T obj) {
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    	Validator validator = factory.getValidator();
    	
    	Set<ConstraintViolation<T>> constraintViolations = validator.validate( obj );
    	
    	for (ConstraintViolation<T> constraintViolation : constraintViolations) {
			
    		log.warn("Entity: [" + constraintViolation.getRootBean().getClass().getCanonicalName() + 
					"] Value: [" + String.valueOf(constraintViolation.getInvalidValue()) + "] Msg: [" +constraintViolation.getMessage() +"]");
		}
    	
	}*/

	
	public void update(final T obj)  {
		showValidationErrors(obj);
		getSession().update(obj);
		getSession().flush();
	}
}
