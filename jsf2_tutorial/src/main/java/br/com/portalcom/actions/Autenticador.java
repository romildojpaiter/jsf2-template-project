package br.com.portalcom.actions;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.portalcom.entity.Usuario;

@Named
@RequestScoped
public class Autenticador {
	
	@Inject
	private Logger logger;
	
	@Inject
	private Usuario usuario;
	
	private void autenticator(){
		
	}

}
