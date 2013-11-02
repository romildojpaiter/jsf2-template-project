package br.com.portalcom.actions;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class MenuSapadminAction {
	
	public String acessoDashboard(){
		return "/sapadmin/protected/dashboard";
	}
	
	public String acessoModeloList(){
		return "/sapadmin/protected/tabelas/modelosList";
	}

	public String acessoAcompanhantesList(){
		return "/sapadmin/protected/tabelas/acompanhantesList";
	}

}
