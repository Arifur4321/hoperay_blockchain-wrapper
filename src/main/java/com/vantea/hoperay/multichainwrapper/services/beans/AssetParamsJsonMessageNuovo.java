package com.vantea.hoperay.multichainwrapper.services.beans;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AssetParamsJsonMessageNuovo {

	

	@Override
	public String toString() {
		return "AssetParamsJsonMessageNuovo [value=" + value + ", userName=" + userName + ", idFascicolo=" + idFascicolo
				+ ", idMessaggioAlert=" + idMessaggioAlert + ", tipo_oprazione=" + tipo_oprazione + ", descrizione="
				+ descrizione + ", nuovoAlert=" + nuovoAlert + "]";
	}
	private int value;
	private String userName ;
	private String idFascicolo;
	private String idMessaggioAlert;
	private String tipo_oprazione;
	private String descrizione;
	
	private String nuovoAlert ;
	
	
	public String getNuovoAlert() {
		return nuovoAlert;
	}
	public void setNuovoAlert(String nuovoAlert) {
		this.nuovoAlert = nuovoAlert;
	}
	
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
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
	public String getTipo_oprazione() {
		return tipo_oprazione;
	}
	public void setTipo_oprazione(String tipo_oprazione) {
		this.tipo_oprazione = tipo_oprazione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
}
