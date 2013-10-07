package br.com.portalcom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.portalcom.core.entitys.AbstractEntity;

@Entity
@Table(name="role")
@SequenceGenerator(name="role_sqc", sequenceName = "role_sqc", allocationSize = 1)
public class Role implements AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="role_sqc")
	private Long idRole;
	
	private String descricaoPermissao;

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getDescricaoPermissao() {
		return descricaoPermissao;
	}

	public void setDescricaoPermissao(String descricaPermissao) {
		this.descricaoPermissao = descricaPermissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRole == null) ? 0 : idRole.hashCode());
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
		Role other = (Role) obj;
		if (idRole == null) {
			if (other.idRole != null)
				return false;
		} else if (!idRole.equals(other.idRole))
			return false;
		return true;
	}

	@Override
	public Number getId() {
		return this.idRole;
	}

}