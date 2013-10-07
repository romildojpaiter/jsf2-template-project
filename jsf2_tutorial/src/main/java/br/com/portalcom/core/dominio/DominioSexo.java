package br.com.portalcom.core.dominio;

import org.apache.commons.lang.StringUtils;

import br.com.portalcom.core.dominio.inter.BaseDominioInterface;

public class DominioSexo {

	
	public enum DOMINIO_SEXO implements BaseDominioInterface<DOMINIO_SEXO> {
		
		MASCULINO("Masculino"),
		FEMININO("Feminino"),
		TRANSEXUAL("Transexual");
		
		private String descricao;
		private String longDesc;
		private String description;
		
		private DOMINIO_SEXO(String descricao) {
			this.descricao = descricao;
		}
				 
		private DOMINIO_SEXO(String descricao, String longDesc, String description) {
			this.descricao   = descricao;
			this.longDesc    = longDesc;
			this.description = description;
		}

		
		public String getLongDesc() {
			return (StringUtils.isBlank(this.longDesc)) ? this.descricao : this.longDesc;
		}

		public Integer getOrdinal() {
			return this.ordinal();
		}

		public String getName() {
			return this.name();
		}

		public String getDesc() {
			return this.descricao;
		}

		public Integer getSize() {			
			return values().length;
		}

		public boolean isValido(int cdItemDominio) {
			return (getSize() > cdItemDominio) ? true : false;
		}

		public DOMINIO_SEXO valueOf(int ord) {
			return values()[ord];
		}
	}
}
