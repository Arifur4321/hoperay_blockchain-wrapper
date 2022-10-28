package com.vantea.hoperay.multichainwrapper.services.beans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AssetParamJsonCambioStatoFasciolo {


	
	private String UserName ;
	private String IdFascicolo;
	private String StatoOld;
	private String StatoNew;
	
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

	@Override
	public String toString() {
		return "AssetParamJsonCambioStatoFasciolo [UserName=" + UserName + ", IdFascicolo=" + IdFascicolo
				+ ", StatoOld=" + StatoOld + ", StatoNew=" + StatoNew + "]";
	}
}
