package br.com.portalcom.actions;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.portalcom.entity.Usuario;

@Named
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private transient Logger logger;
	
	private Usuario usuario;
	
	@PostConstruct
	private void init(){
		usuario = new Usuario();
	}

}
