package br.com.sapecasmt.core.providerws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sapecasmt.core.dao.provider.AlunoDAO;
import br.com.sapecasmt.entity.Aluno;

@Path("/alunos")
public class AlunoProviderWS {
	
	AlunoDAO alunoDAO = new AlunoDAO();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Aluno> getAll() {
		return alunoDAO.getAll();
    }    

    @GET
    @Path("{alunoid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Aluno getById(@PathParam("alunoid") int alunoId) {
    	return alunoDAO.findById(alunoId);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno addAluno(Aluno aluno) {
	Aluno response = alunoDAO.saveOrUpdateAluno(aluno);
        return response;
    }
    
    @DELETE
    @Path("{alunoid}")
    public void deleteAluno(@PathParam("alunoid") int alunoId) {
    	alunoDAO.deleteAluno(alunoId);
    }	
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Aluno> getAll(){
		return AlunoDAO.getAll();
	}
	
	@GET
	@Path("alunoid")
	@Produces(MediaType.APPLICATION_JSON)
	public Aluno getAlunoById(@PathParam("alunoid") Integer idAluno){
		return AlunoDAO.getAlunoById(idAluno);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Aluno addAluno(Aluno aluno){
		Aluno response = AlunoDAO.saveOrUpdate(aluno);
		return response;
	}*/

}
