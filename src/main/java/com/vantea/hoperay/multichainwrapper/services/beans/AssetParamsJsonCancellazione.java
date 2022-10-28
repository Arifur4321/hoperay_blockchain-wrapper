package com.vantea.hoperay.multichainwrapper.services.beans;

public class AssetParamsJsonCancellazione {
	

	@Override
	public String toString() {
		return "AssetParamsJsonCancellazione [value=" + value + ", userName=" + userName + ", idFascicolo="
				+ idFascicolo + ", idMessaggioAlert=" + idMessaggioAlert + ", statoCancellazione=" + statoCancellazione
				+ ", descrizione=" + descrizione + ", tipo_oprazione=" + tipo_oprazione + "]";
	}
	private int value ;
	private String userName ;
	private String idFascicolo;
	private String idMessaggioAlert ;
	private String statoCancellazione ;
	private String descrizione; 
	private String tipo_oprazione;

			
	
	
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
	public String getStatoCancellazione() {
		return statoCancellazione;
	}
	public void setStatoCancellazione(String statoCancellazione) {
		this.statoCancellazione = statoCancellazione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getTipo_oprazione() {
		return tipo_oprazione;
	}
	public void setTipo_oprazione(String tipo_oprazione) {
		this.tipo_oprazione = tipo_oprazione;
	}

	}
