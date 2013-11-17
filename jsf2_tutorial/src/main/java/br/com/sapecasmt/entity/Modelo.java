package br.com.sapecasmt.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Email;

import br.com.portalcom.core.dominio.DominioSexo.DOMINIO_SEXO;
import br.com.portalcom.core.entitys.AbstractEntity;

@Entity
@Table(name="modelo")
@SequenceGenerator(name="modelo_seq", sequenceName="modelo_seq", allocationSize=1)
public class Modelo implements AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="modelo_seq")
	private Integer idModelo;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String cpf;
	
	@NotNull
	@Email
	private String email;

	@NotNull
	private String registroGeral;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;
	
	@NotNull
	private String orgaoEmissor;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Enumerated(EnumType.STRING)
	private DOMINIO_SEXO sexo;
	
	@OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable (
        name = "modelo_telefone", 
        joinColumns = {@JoinColumn (name = "idModelo")}, 
        inverseJoinColumns = {@JoinColumn (name = "idTelefone")})
	// @ForeignKey(name="fk_modelo_telefone", inverseName="fk_telefone_modelo")
	private List<Telefone> telefones;
	
	@Embedded
	private Endereco endereco;
	
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Version
	private Integer version;
	

	public Modelo() {
		this.telefones = new ArrayList<Telefone>();
		this.endereco = new Endereco();
	}
	

	public Integer getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Integer idModelo) {
		this.idModelo = idModelo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getRegistroGeral() {
		return registroGeral;
	}

	public void setRegistroGeral(String registroGeral) {
		this.registroGeral = registroGeral;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idModelo == null) ? 0 : idModelo.hashCode());
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
		Modelo other = (Modelo) obj;
		if (idModelo == null) {
			if (other.idModelo != null)
				return false;
		} else if (!idModelo.equals(other.idModelo))
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
		return this.idModelo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public DOMINIO_SEXO getSexo() {
		return sexo;
	}

	public void setSexo(DOMINIO_SEXO sexo) {
		this.sexo = sexo;
	}
	
	public List<Telefone> getListaTelefone(){
		return new ArrayList<Telefone>(this.telefones);
	}
	

}
