package br.com.sapecasmt.actions;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.sapecasmt.dao.ModeloDAO;
import br.com.sapecasmt.entity.Modelo;
import br.com.portalcom.core.qualifier.PortalcomDAO;

@Named
@RequestScoped
public class ModeloMB {

	@Inject
	private Logger logger;

	private Modelo modelo = new Modelo();
	
	@Inject 
	@PortalcomDAO
	private ModeloDAO modeloDAO;
	

	public String cancelar(){
		return "/sapadmin/protected/tabelas/modelosList?faces-redirect=true";
	}

	public String salvar(){
		
		modeloDAO.saveOrUpdate(modelo);
		
		return "/sapadmin/protected/tabelas/modelosList?faces-redirect=true";
	}
	
	public void deletar(){
		modeloDAO.delete(modelo);
	}


	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
}
