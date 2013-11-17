package br.com.sapecasmt.providerws;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.portalcom.core.qualifier.PortalcomDAO;
import br.com.sapecasmt.dao.ModeloDAO;
import br.com.sapecasmt.dao.inter.IModeloDAO;
import br.com.sapecasmt.entity.Modelo;

@Path("/modelos")
public class ModeloProviderWS {
	
	
	@Inject
	@PortalcomDAO
	IModeloDAO modeloDAO = new ModeloDAO(); 
	
	@GET
    @Path("{modeloid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Modelo getById(@PathParam("modeloid") int modeloID) {
    	return modeloDAO.loadById(modeloID);
    }
    
    
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Modelo> getAll() {
		return modeloDAO.findAll();
    }

}
