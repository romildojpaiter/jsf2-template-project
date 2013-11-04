package br.com.portalcom.actions;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.portalcom.entity.Usuario;
import java.io.Serializable;

@Named
@SessionScoped
public class Autenticador implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;
	
	@Inject
	private Usuario usuario;
	
	public Autenticador() {
		super();
	}
	
	public String logout(){
		return "/sapadmin/login";
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
