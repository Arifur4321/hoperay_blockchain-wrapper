package com.vantea.hoperay.multichainwrapper.services.beans.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Cambiostatofascicolobean {



	@NotNull
	@NotEmpty
	private String address_to;
		

	
	private String UserName ;

	private String IdFascicolo;

	private String StatoOld;

	private String StatoNew;
	
	private String descrizione; 


	 
	@Override
	public String toString() {
		return "Cambiostatofascicolobean [address_to=" + address_to + ", UserName=" + UserName + ", IdFascicolo="
				+ IdFascicolo + ", StatoOld=" + StatoOld + ", StatoNew=" + StatoNew + ", descrizione=" + descrizione
				+ "]";
	}

	public String getAddress_to() {
		return address_to;
	}

	public void setAddress_to(String address_to) {
		this.address_to = address_to;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getIdFascicolo() {
		return IdFascicolo;
	}

	public void setIdFascicolo(String idFascicolo) {
		IdFascicolo = idFascicolo;
	}

	public String getStatoOld() {
		return StatoOld;
	}

	public void setStatoOld(String statoOld) {
		StatoOld = statoOld;
	}

	public String getStatoNew() {
		return StatoNew;
	}

	public void setStatoNew(String statoNew) {
		StatoNew = statoNew;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


}
