package com.vantea.hoperay.multichainwrapper.services.beans.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InplaceMessagebeanNuovo {

	
	@Override
	public String toString() {
		return "InplaceMessagebean [address_to=" + address_to + ", userName=" + userName + ", idFascicolo="
				+ idFascicolo + ", idMessaggioAlert=" + idMessaggioAlert + ", descrizione=" + descrizione
				+ ", NuovoAlert=" + NuovoAlert + "]";
	}
	@NotNull
	@NotEmpty
	private String address_to;
	
	
	private String userName ;
	private String idFascicolo;
	private String idMessaggioAlert;
	private String descrizione; 
	private String NuovoAlert ;

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
	public String getIdMessaggioAlert() {
		return idMessaggioAlert;
	}
	public void setIdMessaggioAlert(String idMessaggioAlert) {
		this.idMessaggioAlert = idMessaggioAlert;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getNuovoAlert() {
		return NuovoAlert;
	}
	public void setNuovoAlert(String nuovoAlert) {
		NuovoAlert = nuovoAlert;
	}
	
}
