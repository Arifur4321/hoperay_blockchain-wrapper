package com.vantea.hoperay.multichainwrapper.services.beans.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InplaceDocumentobean {
	
	@NotNull
	@NotEmpty
	private String address_to;
	

	private String userName ;
	private String idFascicolo ;
	private String idDocumento ;
	private String nomeFileDoc ;
	private String descrizioneTipoDoc ;
	 
	private String descrizione; 

 


	public String getAddress_to() {
		return address_to;
	}

	public void setAddress_to(String address_to) {
		this.address_to = address_to;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdFascicolo() {
		return idFascicolo;
	}

	public void setIdFascicolo(String idFascicolo) {
		this.idFascicolo = idFascicolo;
	}

	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getNomeFileDoc() {
		return nomeFileDoc;
	}

	public void setNomeFileDoc(String nomeFileDoc) {
		this.nomeFileDoc = nomeFileDoc;
	}

	public String getDescrizioneTipoDoc() {
		return descrizioneTipoDoc;
	}

	public void setDescrizioneTipoDoc(String descrizioneTipoDoc) {
		this.descrizioneTipoDoc = descrizioneTipoDoc;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "InplaceDocumentobean [address_to=" + address_to + ", userName=" + userName + ", idFascicolo="
				+ idFascicolo + ", idDocumento=" + idDocumento + ", nomeFileDoc=" + nomeFileDoc
				+ ", descrizioneTipoDoc=" + descrizioneTipoDoc + ", descrizione=" + descrizione + "]";
	}

	
}
