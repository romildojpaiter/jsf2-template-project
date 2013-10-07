package br.com.sapecasmt.entity;

import java.util.Date;

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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import br.com.portalcom.core.dominio.DominioAtivoInativo.DOMINIO_ATIVO_INATIVO;
import br.com.portalcom.core.entitys.AbstractEntity;

@Entity
@Table(name="assinante",
		uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
@SequenceGenerator(name="assinante_seq", sequenceName="assinante_seq", allocationSize=1)
public class Assinante implements AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="assinante_seq")
	private Integer idAssinante;
	
	@NotNull
	private String nome;
	
	@NotNull
	@ForeignKey(name="fk_username")
	private String username;
	
	@NotNull
	@Length(min=6,max=12,message="Sua senha deve conter entre {min} e {max} caracteres")
	private String password;
	
	@Transient
	private String verifPassword;
	
	@Email
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Enumerated(EnumType.STRING)
	private DOMINIO_ATIVO_INATIVO flagSituacao;
	
	@Version
	private Integer version;

	public Integer getIdAssinante() {
		return idAssinante;
	}

	public void setIdAssinante(Integer idAssinante) {
		this.idAssinante = idAssinante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifPassword() {
		return verifPassword;
	}

	public void setVerifPassword(String verifPassword) {
		this.verifPassword = verifPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public DOMINIO_ATIVO_INATIVO getFlagSituacao() {
		return flagSituacao;
	}

	public void setFlagSituacao(DOMINIO_ATIVO_INATIVO flagSituacao) {
		this.flagSituacao = flagSituacao;
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
				+ ((idAssinante == null) ? 0 : idAssinante.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		Assinante other = (Assinante) obj;
		if (idAssinante == null) {
			if (other.idAssinante != null)
				return false;
		} else if (!idAssinante.equals(other.idAssinante))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
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
		return this.idAssinante;
	}
	
	

}

