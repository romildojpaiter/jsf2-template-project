package br.com.sapecasmt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="aluno")
@NamedQueries( {
	@NamedQuery(name="alunoExiste", query="select count(c) from Aluno as c where c.nome like :nomeAluno ")
})
@JsonIgnoreProperties(ignoreUnknown=true)
@XmlRootElement
public class Aluno {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	private String nome;
	
	@NotNull
	private Integer nota;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}
	
	
	public String getQualquerCoisa(){
		return "Qualquer coisa";
	}
}
