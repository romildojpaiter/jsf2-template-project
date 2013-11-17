package br.com.sapecasmt.actions;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.portalcom.core.qualifier.PortalcomDAO;
import br.com.sapecasmt.dao.AcompanhanteDAO;
import br.com.sapecasmt.dao.inter.IAcompanhanteDAO;
import br.com.sapecasmt.entity.Acompanhante;


@Named
@ViewScoped
public class AcompanhanteMB {
	
	private Acompanhante acompanhante = new Acompanhante();
	
	
	@Inject
	@PortalcomDAO
	private IAcompanhanteDAO acompanhanteDAO = new AcompanhanteDAO();
	
	
	public String cancelar(){
		return "/sapadmin/protected/tabelas/acompanhantesList?faces-redirect=true";
	}

	public String salvar(){
		
		try{
			validarAcompanhante();
			acompanhanteDAO.saveOrUpdate(acompanhante);
		}
		catch(javax.validation.ConstraintViolationException cve){
			// FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage("Erro 1", "53"));
			return null;
		}
		
		return "/sapadmin/protected/tabelas/acompanhantesList?faces-redirect=true";
	}

	private void validarAcompanhante() {
		// TODO Auto-generated method stub
		
	}

}
