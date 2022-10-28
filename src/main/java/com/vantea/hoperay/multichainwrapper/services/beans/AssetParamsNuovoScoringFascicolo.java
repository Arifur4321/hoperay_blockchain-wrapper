package com.vantea.hoperay.multichainwrapper.services.beans;

public class AssetParamsNuovoScoringFascicolo {


	private int value;
    private String tipo_oprazione;
	
	private String userName;
    private String idFascicolo;
    private String scoreOld;
    private String scoreNew;
    private String descrizione; 
    

    
	@Override
	public String toString() {
		return "AssetParamsNuovoScoringFascicolo [value=" + value + ", tipo_oprazione=" + tipo_oprazione + ", userName="
				+ userName + ", idFascicolo=" + idFascicolo + ", scoreOld=" + scoreOld + ", scoreNew=" + scoreNew
				+ ", descrizione=" + descrizione + "]";
	}

    
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getTipo_oprazione() {
		return tipo_oprazione;
	}
	public void setTipo_oprazione(String tipo_oprazione) {
		this.tipo_oprazione = tipo_oprazione;
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
	
		    
}
