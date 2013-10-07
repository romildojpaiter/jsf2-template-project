package br.com.portalcom.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Email;

import br.com.portalcom.core.entitys.AbstractEntity;

@Entity
@Table(name="usuario")
@SequenceGenerator(name="usuario_sqc", sequenceName = "usuario_sqc", allocationSize = 1)
public class Usuario implements AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="usuario_sqc")
	private Long idUsuario;

	@Column(unique=true,nullable=false)
	private String userName;
	
	@Column(nullable=false)
	@Size(min=6)
	private String password;
	
	@Column(nullable=false)
	@Size(min=5)
	private String nomeUsuario;
	
	@Email
	@Column(nullable=false)
	private String email;
	
    @OneToMany(fetch=FetchType.LAZY)
    @JoinTable(name="usuario_roles",
    	joinColumns=@JoinColumn(name="idusuario"),
    	inverseJoinColumns=@JoinColumn(name="idrole"))
    @ForeignKey(name="fk_user_rule")
	private Set<Role> roles;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUser) {
		this.idUsuario = idUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
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
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public Long getId() {
		return this.idUsuario;
	}

}

