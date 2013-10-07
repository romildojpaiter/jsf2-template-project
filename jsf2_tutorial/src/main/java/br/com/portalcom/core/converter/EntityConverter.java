package br.com.portalcom.core.converter;


import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@RequestScoped
public class EntityConverter implements Converter {

	private Logger logger = LoggerFactory.getLogger(EntityConverter.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
		if(object == null || object.toString().trim().isEmpty()) return null;
		
		try {
			Class<?> classe = object.getClass();
			Long id = (Long) classe.getMethod("getId").invoke(object);
			
			return classe.getName() + "-" + id;
		} catch (Exception e) {
			logger.error("Erro ao converter entidade em String", e);
			return null;
		}
		
	}
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
		if(string == null || string.isEmpty()) return null;
		
		try {
			String[] values = string.split("-");
			if(values.length == 1){	
				return null;
			}
			return em.find(Class.forName(values[0]), Long.valueOf(values[1]));
			// return JpaUtil.getEntityManager().find(Class.forName(values[0]), Long.valueOf(values[1]));
		} catch (Exception e) {
			logger.error("Erro ao converter String em entidade", e);
			return null;
		}
	}
	
}
