package br.com.sapecasmt.actions;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.portalcom.core.qualifier.PortalcomDAO;
import br.com.sapecasmt.dao.ModeloDAO;
import br.com.sapecasmt.entity.Modelo;
import br.com.sapecasmt.entity.Telefone;

@Named
@ViewScoped
public class ModeloMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	private Modelo modelo = new Modelo();
	
	private Telefone telefone = new Telefone();
	
	@Inject 
	@PortalcomDAO
	private ModeloDAO modeloDAO;

	public String cancelar(){
		return "/sapadmin/protected/tabelas/modelosList?faces-redirect=true";
	}

	public String salvar(){
		
		try{
			validarModelo();
			modeloDAO.saveOrUpdate(modelo);
		}
		catch(javax.validation.ConstraintViolationException cve){
			// FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage("Erro 1", "53"));
			return null;
		}
		
		return "/sapadmin/protected/tabelas/modelosList?faces-redirect=true";
	}
	
	private void validarModelo() {
		if (this.modelo.getDataCadastro() == null){
			this.modelo.setDataCadastro(new Date());
		}
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

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	public void adicionarTelefone(){
		this.modelo.getTelefones().add(telefone);
		telefone = new Telefone();
	}
	
    public String testButtonAction() {
        System.out.println("testButtonAction invoked");
        return "anotherPage.xhtml";
    }

    public void testButtonActionListener(AjaxBehaviorEvent event) {
        System.out.println("testButtonActionListener invoked");
    }	
}


