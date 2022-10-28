package com.vantea.hoperay.multichainwrapper.services.beans;

public class AssetParamsJsonLettura {
 

	private int value;
	   
	private String userName ;
	private String idFascicolo;
	private String idMessaggioAlert;
	private String statoLettura;
	private String tipo_oprazione;
	private String descrizione; 
	


	@Override
	public String toString() {
		return "AssetParamsJsonLettura [value=" + value + ", userName=" + userName + ", idFascicolo=" + idFascicolo
				+ ", idMessaggioAlert=" + idMessaggioAlert + ", statoLettura=" + statoLettura + ", tipo_oprazione="
				+ tipo_oprazione + ", descrizione=" + descrizione + "]";
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
	public String getStatoLettura() {
		return statoLettura;
	}
	public void setStatoLettura(String statoLettura) {
		this.statoLettura = statoLettura;
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
