package com.vantea.hoperay.multichainwrapper.services.beans.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Nuovoscoringfascicolobean {

	

	@NotNull
	@NotEmpty
	private String address_to;

	
	private String userName;
    private String idFascicolo;
    private String scoreOld;
    private String scoreNew;
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
	public String getScoreOld() {
		return scoreOld;
	}
	public void setScoreOld(String scoreOld) {
		this.scoreOld = scoreOld;
	}
	public String getScoreNew() {
		return scoreNew;
	}
	public void setScoreNew(String scoreNew) {
		this.scoreNew = scoreNew;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public String toString() {
		return "Nuovoscoringfascicolobean [address_to=" + address_to + ", userName=" + userName + ", idFascicolo="
				+ idFascicolo + ", scoreOld=" + scoreOld + ", scoreNew=" + scoreNew + ", descrizione=" + descrizione
				+ "]";
	}
		
}
