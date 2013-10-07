package br.com.sapecasmt.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import br.com.portalcom.core.dominio.DominioOperadoraTelefone.DOMINIO_OPERADORA_TELEFONE;

@Entity
@Table(name="telefone")
@SequenceGenerator(name="telefone_seq", sequenceName="telefone_seq", allocationSize=1)
public class Telefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="telefone_seq")
	private Integer idTelefone;

	@Enumerated(EnumType.STRING)
	private DOMINIO_OPERADORA_TELEFONE operadoraTelefone;
	
	@NotNull
	private String numero;
	
	@Version
	private Integer version;

	public Integer getIdTelefone() {
		return idTelefone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idTelefone == null) ? 0 : idTelefone.hashCode());
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
		Telefone other = (Telefone) obj;
		if (idTelefone == null) {
			if (other.idTelefone != null)
				return false;
		} else if (!idTelefone.equals(other.idTelefone))
			return false;
		return true;
	}

	public void setIdTelefone(Integer idTelefone) {
		this.idTelefone = idTelefone;
	}

	public DOMINIO_OPERADORA_TELEFONE getOperadoraTelefone() {
		return operadoraTelefone;
	}

	public void setOperadoraTelefone(DOMINIO_OPERADORA_TELEFONE operadoraTelefone) {
		this.operadoraTelefone = operadoraTelefone;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	

	
	
}
