package br.com.sapecasmt.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InformacoesEspeciais {
	
	@Column(columnDefinition="TEXT")
	private String facoPerfil;
	
	@Column(columnDefinition="TEXT")
	private String acompanho;
	
	@Column(columnDefinition="TEXT")
	private String atendoEm;
	
	@Column(columnDefinition="TEXT")
	private String idiomas;
	
	@Column(columnDefinition="TEXT")
	private String especialidades;

	public String getFacoPerfil() {
		return facoPerfil;
	}

	public void setFacoPerfil(String facoPerfil) {
		this.facoPerfil = facoPerfil;
	}

	public String getAcompanho() {
		return acompanho;
	}

	public void setAcompanho(String acompanho) {
		this.acompanho = acompanho;
	}

	public String getAtendoEm() {
		return atendoEm;
	}

	public void setAtendoEm(String atendoEm) {
		this.atendoEm = atendoEm;
	}

	public String getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}

	public String getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(String especialidades) {
		this.especialidades = especialidades;
	}
	

}
