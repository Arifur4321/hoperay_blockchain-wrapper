package com.vantea.hoperay.multichainwrapper.services.beans.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InplaceCancellazionebean {

	

	@NotNull
	@NotEmpty
	private String address_to;
	
	private String userName ;
	private String idFascicolo;
	private String idMessaggioAlert ;
	private boolean statoCancellazione ;
	
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

	public String getIdMessaggioAlert() {
		return idMessaggioAlert;
	}

	public void setIdMessaggioAlert(String idMessaggioAlert) {
		this.idMessaggioAlert = idMessaggioAlert;
	}

	public boolean isStatoCancellazione() {
		return statoCancellazione;
	}

	public void setStatoCancellazione(boolean statoCancellazione) {
		this.statoCancellazione = statoCancellazione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "InplaceCancellazionebean [address_to=" + address_to + ", userName=" + userName + ", idFascicolo="
				+ idFascicolo + ", idMessaggioAlert=" + idMessaggioAlert + ", statoCancellazione=" + statoCancellazione
				+ ", descrizione=" + descrizione + "]";
	}
	
}
