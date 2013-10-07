package br.com.portalcom.actions;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.portalcom.entity.Usuario;

@Named
@SessionScoped
public class UsuarioController {

	@Inject
	private Logger logger;
	
	@Inject
	private Usuario usuario;

}
