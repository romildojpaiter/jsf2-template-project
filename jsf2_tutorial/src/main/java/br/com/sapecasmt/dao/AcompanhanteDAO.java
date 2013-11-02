package br.com.sapecasmt.dao;

import br.com.portalcom.core.hibernate.HibernateGenericDAO;
import br.com.sapecasmt.dao.inter.IAcompanhanteDAO;
import br.com.sapecasmt.entity.Acompanhante;

public class AcompanhanteDAO extends HibernateGenericDAO<Acompanhante> implements IAcompanhanteDAO {

	private static final long serialVersionUID = 1L;
	
	
	public AcompanhanteDAO(){
		super(Acompanhante.class);
	}

}
