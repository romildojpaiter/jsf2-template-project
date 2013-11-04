package br.com.sapecasmt.dao;

import br.com.portalcom.core.hibernate.HibernateGenericDAO;
import br.com.portalcom.core.qualifier.PortalcomDAO;
import br.com.sapecasmt.dao.inter.IComentarioDAO;
import br.com.sapecasmt.entity.Comentario;

@PortalcomDAO
public class ComentarioDAO extends HibernateGenericDAO<Comentario> implements IComentarioDAO {

	private static final long serialVersionUID = 1L;
	
	public ComentarioDAO(){
		super(Comentario.class);
	}

}
