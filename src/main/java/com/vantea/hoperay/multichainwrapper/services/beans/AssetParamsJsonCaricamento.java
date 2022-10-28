package com.vantea.hoperay.multichainwrapper.services.beans;

public class AssetParamsJsonCaricamento {


	private int value;
	private String userName ;
	private String idFascicolo ;
	private String idDocumento ;
	private String nomeFileDoc ;
	private String descrizioneTipoDoc ;
	private String descrizione; 
	private String tipo_oprazione;
	
	private String statoDocumento;
	 
	
	public String getStatoDocumento() {
		return statoDocumento;
	}
	public void setStatoDocumento(String statoDocumento) {
		this.statoDocumento = statoDocumento;
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
	
	


	public String getTipo_oprazione() {
		return tipo_oprazione;
	}
	public void setTipo_oprazione(String tipo_oprazione) {
		this.tipo_oprazione = tipo_oprazione;
	}
	
	@Override
	public String toString() {
		return "AssetParamsJsonCaricamento [value=" + value + ", userName=" + userName + ", idFascicolo=" + idFascicolo
				+ ", idDocumento=" + idDocumento + ", nomeFileDoc=" + nomeFileDoc + ", descrizioneTipoDoc="
				+ descrizioneTipoDoc + ", descrizione=" + descrizione + ", tipo_oprazione=" + tipo_oprazione
				+ ", statoDocumento=" + statoDocumento + "]";
	}
}
