package br.com.sapecasmt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import br.com.portalcom.core.dominio.DominioSimNao.DOMINIO_SIM_NAO;
import br.com.portalcom.core.entitys.AbstractEntity;

@Entity
@Table(name="comentario")
@SequenceGenerator(name="comentario_seq", sequenceName="comentario_seq", allocationSize=1)
public class Comentario implements AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="comentario_seq")
	private Integer idComentario;
	
	@NotNull
	private String apelido;
	
	@Email
	private String email;
	
	@Enumerated(EnumType.STRING)
	private DOMINIO_SIM_NAO exibeEmail;
	
	@Column(columnDefinition="TEXT")
	private String comentario;
	
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Version
	private Integer version;

	public Integer getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DOMINIO_SIM_NAO getExibeEmail() {
		return exibeEmail;
	}

	public void setExibeEmail(DOMINIO_SIM_NAO exibeEmail) {
		this.exibeEmail = exibeEmail;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idComentario == null) ? 0 : idComentario.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comentario other = (Comentario) obj;
		if (idComentario == null) {
			if (other.idComentario != null)
				return false;
		} else if (!idComentario.equals(other.idComentario))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	@Override
	public Integer getId() {
		return this.idComentario;
	}
	
	


}
