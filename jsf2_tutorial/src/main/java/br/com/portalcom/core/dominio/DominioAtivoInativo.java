package br.com.portalcom.core.dominio;

import org.apache.commons.lang.StringUtils;

import br.com.portalcom.core.dominio.inter.BaseDominioInterface;

public class DominioAtivoInativo {
	
	 public enum DOMINIO_ATIVO_INATIVO implements BaseDominioInterface<DOMINIO_ATIVO_INATIVO>{
				 
		ATIVO("Ativo"),
		INATIVO("Inativo");
		 
		private String descricao;
		private String longDesc;
		private String description;
		
		private DOMINIO_ATIVO_INATIVO(String descricao) {
			this.descricao = descricao;
		}
				 
		private DOMINIO_ATIVO_INATIVO(String descricao, String longDesc, String description) {
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

		public DOMINIO_ATIVO_INATIVO valueOf(int ord) {
			return values()[ord];
		}
		 
	 }

}

