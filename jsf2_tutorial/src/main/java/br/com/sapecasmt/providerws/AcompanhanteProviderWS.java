package br.com.sapecasmt.providerws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sapecasmt.dao.AcompanhanteDAO;
import br.com.sapecasmt.dao.inter.IAcompanhanteDAO;
import br.com.sapecasmt.entity.Acompanhante;
import br.com.sapecasmt.entity.Aluno;

@Path("/acompanhantes")
public class AcompanhanteProviderWS {
	
	IAcompanhanteDAO acompanhanteDAO = new AcompanhanteDAO(); 
	
    @GET
    @Path("{acompanhanteid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Acompanhante getById(@PathParam("acompanhanteid") int acompanhanteId) {
    	return acompanhanteDAO.loadById(acompanhanteId);
    }
    
    
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Acompanhante> getAll() {
		return acompanhanteDAO.findAll();
    }


}
