package br.com.sapecasmt.entity;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ForeignKey;

import br.com.portalcom.core.dominio.DominioSexo.DOMINIO_SEXO;
import br.com.portalcom.core.dominio.DominioSimNao.DOMINIO_SIM_NAO;
import br.com.portalcom.core.entitys.AbstractEntity;

@Entity
@Table(name="perfil")
@SequenceGenerator(name="perfil_seq", sequenceName="perfil_seq", allocationSize=1)
public class Perfil implements AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="perfil_seq", strategy=GenerationType.SEQUENCE)
	private Integer idPerfil;
	
	@OneToOne(optional=false, 
		fetch=FetchType.EAGER, 
		orphanRemoval = true)
	@JoinColumn(name="idModelo", unique=false, nullable=true, insertable=true, updatable=true)
	@ForeignKey(name="fk_modelo_perfil")
	private Modelo modelo;
	
	@NotNull
	private String nomePerfil;

	@OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable (
        name = "perfil_telefone", 
        joinColumns = {@JoinColumn (name = "idPerfil")}, 
        inverseJoinColumns = {@JoinColumn (name = "idTelefone")})
	@ForeignKey(name="fk_perfil_telefone", inverseName="fk_telefone_perfil")
	private Set<Telefone> telefones; 	
	
	@OneToMany(fetch=FetchType.EAGER)
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinTable(name="perfil_imagem",
			joinColumns=@JoinColumn(referencedColumnName="idPerfil"),
			inverseJoinColumns=@JoinColumn(referencedColumnName="idImagem"))
	@ForeignKey(name="fk_perfil_imagem",inverseName="fk_imagem_perfil")
	private Set<Imagem> imagens;
	
	
	@OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable (
        name = "perfil_comentario", 
        joinColumns = {@JoinColumn (name = "idPerfil")}, 
        inverseJoinColumns = {@JoinColumn (name = "idComentario")})
	@ForeignKey(name="fk_perfil_comentario", inverseName="fk_comentario_perfil")
	private Set<Comentario> comentarios;
	
	private String descricaoPessoal;
	
	@Enumerated(EnumType.STRING)
	private DOMINIO_SIM_NAO aceitaCartoes;
	
	@Enumerated(EnumType.STRING)
	private DOMINIO_SIM_NAO diponivelViagens;
	
	@Enumerated(EnumType.STRING)
	private DOMINIO_SIM_NAO permiteFumar;

	@Enumerated(EnumType.STRING)
	private DOMINIO_SEXO sexo;
	
	private Integer idade;
	
	private String regiaoAtendimento;
	
	private String corOlhos;
	
	private String corCabelos;
	
	private String altura;
	
	private Integer peso;
	
	private Integer manequim;
	
	private Integer pes;
	
	
	private String horarioAtendimento;
	
	@Embedded
	private InformacoesEspeciais informacoesEspeciais;
	
	
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Version
	private Integer version;

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public String getDescricaoPessoal() {
		return descricaoPessoal;
	}

	public void setDescricaoPessoal(String descricaoPessoal) {
		this.descricaoPessoal = descricaoPessoal;
	}

	public DOMINIO_SIM_NAO getAceitaCartoes() {
		return aceitaCartoes;
	}

	public void setAceitaCartoes(DOMINIO_SIM_NAO aceitaCartoes) {
		this.aceitaCartoes = aceitaCartoes;
	}

	public DOMINIO_SIM_NAO getDiponivelViagens() {
		return diponivelViagens;
	}

	public void setDiponivelViagens(DOMINIO_SIM_NAO diponivelViagens) {
		this.diponivelViagens = diponivelViagens;
	}

	public DOMINIO_SIM_NAO getPermiteFumar() {
		return permiteFumar;
	}

	public void setPermiteFumar(DOMINIO_SIM_NAO permiteFumar) {
		this.permiteFumar = permiteFumar;
	}

	public DOMINIO_SEXO getSexo() {
		return sexo;
	}

	public void setSexo(DOMINIO_SEXO sexo) {
		this.sexo = sexo;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getRegiaoAtendimento() {
		return regiaoAtendimento;
	}

	public void setRegiaoAtendimento(String regiaoAtendimento) {
		this.regiaoAtendimento = regiaoAtendimento;
	}

	public String getCorOlhos() {
		return corOlhos;
	}

	public void setCorOlhos(String corOlhos) {
		this.corOlhos = corOlhos;
	}

	public String getCorCabelos() {
		return corCabelos;
	}

	public void setCorCabelos(String corCabelos) {
		this.corCabelos = corCabelos;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Integer getManequim() {
		return manequim;
	}

	public void setManequim(Integer manequim) {
		this.manequim = manequim;
	}

	public Integer getPes() {
		return pes;
	}

	public void setPes(Integer pes) {
		this.pes = pes;
	}

	public String getHorarioAtendimento() {
		return horarioAtendimento;
	}

	public void setHorarioAtendimento(String horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
	}

	public InformacoesEspeciais getInformacoesEspeciais() {
		return informacoesEspeciais;
	}

	public void setInformacoesEspeciais(InformacoesEspeciais informacoesEspeciais) {
		this.informacoesEspeciais = informacoesEspeciais;
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
				+ ((idPerfil == null) ? 0 : idPerfil.hashCode());
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
		Perfil other = (Perfil) obj;
		if (idPerfil == null) {
			if (other.idPerfil != null)
				return false;
		} else if (!idPerfil.equals(other.idPerfil))
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
		return this.idPerfil;
	}

	public Set<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(Set<Imagem> imagens) {
		this.imagens = imagens;
	}

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
}
