package br.com.sapecasmt.core.dao.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sapecasmt.entity.Aluno;

public class AlunoDAO {
	
	private static int alunosIds;   
    private static List<Aluno> todosAlunos = new ArrayList<Aluno>();
    private static Map<Integer, Aluno> todosAlunosPorId = new HashMap<Integer, Aluno>();
    
    public AlunoDAO() {
        Aluno sherlock = new Aluno();
        sherlock.setId(1);
        sherlock.setNome("Sherlock Holmes");
        sherlock.setNota(10);
        todosAlunos.add(sherlock);
        sherlock.setId(2);
        Aluno watson = new Aluno();
        watson.setNome("Watson");
        watson.setNota(9);
        todosAlunos.add(watson);
    }
    
    public Aluno saveOrUpdateAluno(Aluno aluno ){
        if (aluno.getId() <= 0) {
            aluno.setId(++alunosIds);
        } else {
            deleteAluno(aluno.getId());
        }
        todosAlunos.add(aluno);
        todosAlunosPorId.put(aluno.getId(), aluno);
        return aluno;
    }
    
    public void deleteAluno(int alunoId) {
        Aluno aluno = todosAlunosPorId.get(alunoId);
        if (aluno != null) {
            todosAlunosPorId.remove(aluno.getId());
            todosAlunos.remove(aluno);
        }
    }
    
    public  Aluno findById(int alunoId) {
        return todosAlunosPorId.get(alunoId);
    }
    
    public List<Aluno> getAll() {
        return todosAlunos;
    }     	
	

	/*public static List<Aluno> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Aluno getAlunoById(Integer idAluno) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Aluno saveOrUpdate(Aluno aluno) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
