package br.com.portalcom.core.dominio;

import org.apache.commons.lang.StringUtils;

import br.com.portalcom.core.dominio.inter.BaseDominioInterface;

public class DominioOperadoraTelefone {
	
	
	public enum DOMINIO_OPERADORA_TELEFONE implements BaseDominioInterface<DOMINIO_OPERADORA_TELEFONE> {
		
		TIM("Tim"),
		VIVO("Vivo"),
		CLARO("Claro"),
		OI("OI");
		
		
		private String descricao;
		private String longDesc;
		private String description;
		
		private DOMINIO_OPERADORA_TELEFONE(String descricao) {
			this.descricao = descricao;
		}
				 
		private DOMINIO_OPERADORA_TELEFONE(String descricao, String longDesc, String description) {
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

		public DOMINIO_OPERADORA_TELEFONE valueOf(int ord) {
			return values()[ord];
		}
		
	}
}
