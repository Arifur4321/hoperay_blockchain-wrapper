package com.vantea.hoperay.multichainwrapper.services.beans;

import java.util.*;
 

public class AssetParamsJSON {



	private int value;
	   
	private String userName ;
	private String idFascicolo;
	private String statoOld;
	private String statoNew;
	private String tipo_oprazione;
	private String descrizione; 



	
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
	public String getStatoOld() {
		return statoOld;
	}
	public void setStatoOld(String statoOld) {
		this.statoOld = statoOld;
	}
	public String getStatoNew() {
		return statoNew;
	}
	public void setStatoNew(String statoNew) {
		this.statoNew = statoNew;
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
	@Override
	public String toString() {
		return "AssetParamsJSON [value=" + value + ", userName=" + userName + ", idFascicolo=" + idFascicolo
				+ ", statoOld=" + statoOld + ", statoNew=" + statoNew + ", tipo_oprazione=" + tipo_oprazione
				+ ", descrizione=" + descrizione + "]";
	}
	    
		
	}
