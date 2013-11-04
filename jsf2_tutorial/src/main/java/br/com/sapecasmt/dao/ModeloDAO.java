package br.com.sapecasmt.dao;

import br.com.portalcom.core.hibernate.HibernateGenericDAO;
import br.com.portalcom.core.qualifier.PortalcomDAO;
import br.com.sapecasmt.dao.inter.IModeloDAO;
import br.com.sapecasmt.entity.Modelo;

@PortalcomDAO
public class ModeloDAO extends HibernateGenericDAO<Modelo> implements IModeloDAO {

	private static final long serialVersionUID = 1L;
	
	public ModeloDAO(){
		super(Modelo.class);
	}

}
