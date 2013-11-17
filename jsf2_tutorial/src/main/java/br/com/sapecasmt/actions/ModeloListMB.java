package br.com.sapecasmt.actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.portalcom.core.qualifier.PortalcomDAO;
import br.com.sapecasmt.dao.inter.IModeloDAO;
import br.com.sapecasmt.entity.Modelo;

@Named
@RequestScoped
public class ModeloListMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Logger logger;
	
	@Inject 
	@PortalcomDAO
	private IModeloDAO modeloDAO;
		

	private List<Modelo> modelos = new ArrayList<Modelo>();

	public List<Modelo> getModelos() {
		if(modelos.isEmpty()){
			pesquisarModelo();
		}
		return modelos;
	}

	private void pesquisarModelo(){
		modelos.addAll(modeloDAO.findAll());
	}
	
	public String adicionarNovaModelo(){
		return "/sapadmin/protected/tabelas/modeloEditar?faces-redirect=true";
	}
	
}
