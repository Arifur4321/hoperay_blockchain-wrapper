package com.vantea.hoperay.multichainwrapper.services.beans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InpPlaceTfascicolo {
	
	@NotNull
	@NotEmpty
	private String address_from;
	
	
	public String Fascicolo_Id;
	public String Fascicolo_Codice;
	public String Fascicolo_Tipo;
	public String Fascicolo_Stato;
	public String Fascicolo_Descrizione;
	public String Fascicolo_Note;
	public String Fascicolo_GruppoFascicoli_Id;
	public String Fascicolo_TipoFascicolo_Id;
	public String Fascicolo_TipoPratica_Id;
	public String Fascicolo_IdPratica;
	public String Fascicolo_CodiceSoggetto;
	public String Fascicolo_IdentificativoEsterno;
	public String Fascicolo_CodiceFascicolo;
	public String Fascicolo_AziendaMittente_Id;
	public String Fascicolo_Ins_DataSys;
	public String Fascicolo_DataRif;
	public String Fascicolo_ECM_Node;
	public String Fascicolo_MetaData;
	public String Fascicolo_Fine_DataSys;
	public String Fascicolo_ScoreCalcolato;
	public String Fascicolo_ScoreManuale;
	public String Fascicolo_ScoreCalcolato_DataSys;
	public String Fascicolo_ScoreManuale_DataSys;
	public String Fascicolo_ScoreManuale_UserId;
	public String Fascicolo_StatoCalcolato_TipoStatoFascicolo_Id;
	public String Fascicolo_StatoCalcolato_DataSys;
	public String Fascicolo_StatoManuale_TipoStatoFascicolo_Id;
	public String Fascicolo_StatoManuale_DataSys;
	public String Fascicolo_StatoManuale_Username;
	public String Fascicolo_Valore;
	public String Fascicolo_StatoManuale_UserId;
	public String Fascicolo_ScoreCalcolato_UserId;
	public String Fascicolo_ScoreCalcolato_Username;
	public String Fascicolo_StatoCalcolato_UserId;
	public String Fascicolo_StatoCalcolato_Username;
	public String Fascicolo_FileGuida_Id;
	public String Fascicolo_RigaFileGuida;
	public String Fascicolo_ScoreEffettivo;
	public String Fascicolo_ScoreEffettivo_DataSys;
	public String Fascicolo_ScoreEffettivo_UserId;
	public String Fascicolo_ScoreEffettivo_Username;
	public String Fascicolo_StatoEffettivo_TipoStatoFascicolo_Id;
	public String Fascicolo_StatoEffettivo_DataSys;
	public String Fascicolo_StatoEffettivo_UserId;
	public String Fascicolo_StatoEffettivo_Username;

	
	public String getAddress_from() {
		return address_from;
	}
	public void setAddress_from(String address_from) {
		this.address_from = address_from;
	}
	
	public String getFascicolo_Id() {
		return Fascicolo_Id;
	}
	public void setFascicolo_Id(String fascicolo_Id) {
		Fascicolo_Id = fascicolo_Id;
	}
	public String getFascicolo_Codice() {
		return Fascicolo_Codice;
	}
	public void setFascicolo_Codice(String fascicolo_Codice) {
		Fascicolo_Codice = fascicolo_Codice;
	}
	public String getFascicolo_Tipo() {
		return Fascicolo_Tipo;
	}
	public void setFascicolo_Tipo(String fascicolo_Tipo) {
		Fascicolo_Tipo = fascicolo_Tipo;
	}
	public String getFascicolo_Stato() {
		return Fascicolo_Stato;
	}
	public void setFascicolo_Stato(String fascicolo_Stato) {
		Fascicolo_Stato = fascicolo_Stato;
	}
	public String getFascicolo_Descrizione() {
		return Fascicolo_Descrizione;
	}
	public void setFascicolo_Descrizione(String fascicolo_Descrizione) {
		Fascicolo_Descrizione = fascicolo_Descrizione;
	}
	public String getFascicolo_Note() {
		return Fascicolo_Note;
	}
	public void setFascicolo_Note(String fascicolo_Note) {
		Fascicolo_Note = fascicolo_Note;
	}
	public String getFascicolo_GruppoFascicoli_Id() {
		return Fascicolo_GruppoFascicoli_Id;
	}
	public void setFascicolo_GruppoFascicoli_Id(String fascicolo_GruppoFascicoli_Id) {
		Fascicolo_GruppoFascicoli_Id = fascicolo_GruppoFascicoli_Id;
	}
	public String getFascicolo_TipoFascicolo_Id() {
		return Fascicolo_TipoFascicolo_Id;
	}
	public void setFascicolo_TipoFascicolo_Id(String fascicolo_TipoFascicolo_Id) {
		Fascicolo_TipoFascicolo_Id = fascicolo_TipoFascicolo_Id;
	}
	public String getFascicolo_TipoPratica_Id() {
		return Fascicolo_TipoPratica_Id;
	}
	public void setFascicolo_TipoPratica_Id(String fascicolo_TipoPratica_Id) {
		Fascicolo_TipoPratica_Id = fascicolo_TipoPratica_Id;
	}
	public String getFascicolo_IdPratica() {
		return Fascicolo_IdPratica;
	}
	public void setFascicolo_IdPratica(String fascicolo_IdPratica) {
		Fascicolo_IdPratica = fascicolo_IdPratica;
	}
	public String getFascicolo_CodiceSoggetto() {
		return Fascicolo_CodiceSoggetto;
	}
	public void setFascicolo_CodiceSoggetto(String fascicolo_CodiceSoggetto) {
		Fascicolo_CodiceSoggetto = fascicolo_CodiceSoggetto;
	}
	public String getFascicolo_IdentificativoEsterno() {
		return Fascicolo_IdentificativoEsterno;
	}
	public void setFascicolo_IdentificativoEsterno(String fascicolo_IdentificativoEsterno) {
		Fascicolo_IdentificativoEsterno = fascicolo_IdentificativoEsterno;
	}
	public String getFascicolo_CodiceFascicolo() {
		return Fascicolo_CodiceFascicolo;
	}
	public void setFascicolo_CodiceFascicolo(String fascicolo_CodiceFascicolo) {
		Fascicolo_CodiceFascicolo = fascicolo_CodiceFascicolo;
	}
	public String getFascicolo_AziendaMittente_Id() {
		return Fascicolo_AziendaMittente_Id;
	}
	public void setFascicolo_AziendaMittente_Id(String fascicolo_AziendaMittente_Id) {
		Fascicolo_AziendaMittente_Id = fascicolo_AziendaMittente_Id;
	}
	public String getFascicolo_Ins_DataSys() {
		return Fascicolo_Ins_DataSys;
	}
	public void setFascicolo_Ins_DataSys(String fascicolo_Ins_DataSys) {
		Fascicolo_Ins_DataSys = fascicolo_Ins_DataSys;
	}
	public String getFascicolo_DataRif() {
		return Fascicolo_DataRif;
	}
	public void setFascicolo_DataRif(String fascicolo_DataRif) {
		Fascicolo_DataRif = fascicolo_DataRif;
	}
	public String getFascicolo_ECM_Node() {
		return Fascicolo_ECM_Node;
	}
	public void setFascicolo_ECM_Node(String fascicolo_ECM_Node) {
		Fascicolo_ECM_Node = fascicolo_ECM_Node;
	}
	public String getFascicolo_MetaData() {
		return Fascicolo_MetaData;
	}
	public void setFascicolo_MetaData(String fascicolo_MetaData) {
		Fascicolo_MetaData = fascicolo_MetaData;
	}
	public String getFascicolo_Fine_DataSys() {
		return Fascicolo_Fine_DataSys;
	}
	public void setFascicolo_Fine_DataSys(String fascicolo_Fine_DataSys) {
		Fascicolo_Fine_DataSys = fascicolo_Fine_DataSys;
	}
	public String getFascicolo_ScoreCalcolato() {
		return Fascicolo_ScoreCalcolato;
	}
	public void setFascicolo_ScoreCalcolato(String fascicolo_ScoreCalcolato) {
		Fascicolo_ScoreCalcolato = fascicolo_ScoreCalcolato;
	}
	public String getFascicolo_ScoreManuale() {
		return Fascicolo_ScoreManuale;
	}
	public void setFascicolo_ScoreManuale(String fascicolo_ScoreManuale) {
		Fascicolo_ScoreManuale = fascicolo_ScoreManuale;
	}
	public String getFascicolo_ScoreCalcolato_DataSys() {
		return Fascicolo_ScoreCalcolato_DataSys;
	}
	public void setFascicolo_ScoreCalcolato_DataSys(String fascicolo_ScoreCalcolato_DataSys) {
		Fascicolo_ScoreCalcolato_DataSys = fascicolo_ScoreCalcolato_DataSys;
	}
	public String getFascicolo_ScoreManuale_DataSys() {
		return Fascicolo_ScoreManuale_DataSys;
	}
	public void setFascicolo_ScoreManuale_DataSys(String fascicolo_ScoreManuale_DataSys) {
		Fascicolo_ScoreManuale_DataSys = fascicolo_ScoreManuale_DataSys;
	}
	public String getFascicolo_ScoreManuale_UserId() {
		return Fascicolo_ScoreManuale_UserId;
	}
	public void setFascicolo_ScoreManuale_UserId(String fascicolo_ScoreManuale_UserId) {
		Fascicolo_ScoreManuale_UserId = fascicolo_ScoreManuale_UserId;
	}
	public String getFascicolo_StatoCalcolato_TipoStatoFascicolo_Id() {
		return Fascicolo_StatoCalcolato_TipoStatoFascicolo_Id;
	}
	public void setFascicolo_StatoCalcolato_TipoStatoFascicolo_Id(String fascicolo_StatoCalcolato_TipoStatoFascicolo_Id) {
		Fascicolo_StatoCalcolato_TipoStatoFascicolo_Id = fascicolo_StatoCalcolato_TipoStatoFascicolo_Id;
	}
	public String getFascicolo_StatoCalcolato_DataSys() {
		return Fascicolo_StatoCalcolato_DataSys;
	}
	public void setFascicolo_StatoCalcolato_DataSys(String fascicolo_StatoCalcolato_DataSys) {
		Fascicolo_StatoCalcolato_DataSys = fascicolo_StatoCalcolato_DataSys;
	}
	public String getFascicolo_StatoManuale_TipoStatoFascicolo_Id() {
		return Fascicolo_StatoManuale_TipoStatoFascicolo_Id;
	}
	public void setFascicolo_StatoManuale_TipoStatoFascicolo_Id(String fascicolo_StatoManuale_TipoStatoFascicolo_Id) {
		Fascicolo_StatoManuale_TipoStatoFascicolo_Id = fascicolo_StatoManuale_TipoStatoFascicolo_Id;
	}
	public String getFascicolo_StatoManuale_DataSys() {
		return Fascicolo_StatoManuale_DataSys;
	}
	public void setFascicolo_StatoManuale_DataSys(String fascicolo_StatoManuale_DataSys) {
		Fascicolo_StatoManuale_DataSys = fascicolo_StatoManuale_DataSys;
	}
	public String getFascicolo_StatoManuale_Username() {
		return Fascicolo_StatoManuale_Username;
	}
	public void setFascicolo_StatoManuale_Username(String fascicolo_StatoManuale_Username) {
		Fascicolo_StatoManuale_Username = fascicolo_StatoManuale_Username;
	}
	public String getFascicolo_Valore() {
		return Fascicolo_Valore;
	}
	public void setFascicolo_Valore(String fascicolo_Valore) {
		Fascicolo_Valore = fascicolo_Valore;
	}
	public String getFascicolo_StatoManuale_UserId() {
		return Fascicolo_StatoManuale_UserId;
	}
	public void setFascicolo_StatoManuale_UserId(String fascicolo_StatoManuale_UserId) {
		Fascicolo_StatoManuale_UserId = fascicolo_StatoManuale_UserId;
	}
	public String getFascicolo_ScoreCalcolato_UserId() {
		return Fascicolo_ScoreCalcolato_UserId;
	}
	public void setFascicolo_ScoreCalcolato_UserId(String fascicolo_ScoreCalcolato_UserId) {
		Fascicolo_ScoreCalcolato_UserId = fascicolo_ScoreCalcolato_UserId;
	}
	public String getFascicolo_ScoreCalcolato_Username() {
		return Fascicolo_ScoreCalcolato_Username;
	}
	public void setFascicolo_ScoreCalcolato_Username(String fascicolo_ScoreCalcolato_Username) {
		Fascicolo_ScoreCalcolato_Username = fascicolo_ScoreCalcolato_Username;
	}
	public String getFascicolo_StatoCalcolato_UserId() {
		return Fascicolo_StatoCalcolato_UserId;
	}
	public void setFascicolo_StatoCalcolato_UserId(String fascicolo_StatoCalcolato_UserId) {
		Fascicolo_StatoCalcolato_UserId = fascicolo_StatoCalcolato_UserId;
	}
	public String getFascicolo_StatoCalcolato_Username() {
		return Fascicolo_StatoCalcolato_Username;
	}
	public void setFascicolo_StatoCalcolato_Username(String fascicolo_StatoCalcolato_Username) {
		Fascicolo_StatoCalcolato_Username = fascicolo_StatoCalcolato_Username;
	}
	public String getFascicolo_FileGuida_Id() {
		return Fascicolo_FileGuida_Id;
	}
	public void setFascicolo_FileGuida_Id(String fascicolo_FileGuida_Id) {
		Fascicolo_FileGuida_Id = fascicolo_FileGuida_Id;
	}
	public String getFascicolo_RigaFileGuida() {
		return Fascicolo_RigaFileGuida;
	}
	public void setFascicolo_RigaFileGuida(String fascicolo_RigaFileGuida) {
		Fascicolo_RigaFileGuida = fascicolo_RigaFileGuida;
	}
	public String getFascicolo_ScoreEffettivo() {
		return Fascicolo_ScoreEffettivo;
	}
	public void setFascicolo_ScoreEffettivo(String fascicolo_ScoreEffettivo) {
		Fascicolo_ScoreEffettivo = fascicolo_ScoreEffettivo;
	}
	public String getFascicolo_ScoreEffettivo_DataSys() {
		return Fascicolo_ScoreEffettivo_DataSys;
	}
	public void setFascicolo_ScoreEffettivo_DataSys(String fascicolo_ScoreEffettivo_DataSys) {
		Fascicolo_ScoreEffettivo_DataSys = fascicolo_ScoreEffettivo_DataSys;
	}
	public String getFascicolo_ScoreEffettivo_UserId() {
		return Fascicolo_ScoreEffettivo_UserId;
	}
	public void setFascicolo_ScoreEffettivo_UserId(String fascicolo_ScoreEffettivo_UserId) {
		Fascicolo_ScoreEffettivo_UserId = fascicolo_ScoreEffettivo_UserId;
	}
	public String getFascicolo_ScoreEffettivo_Username() {
		return Fascicolo_ScoreEffettivo_Username;
	}
	public void setFascicolo_ScoreEffettivo_Username(String fascicolo_ScoreEffettivo_Username) {
		Fascicolo_ScoreEffettivo_Username = fascicolo_ScoreEffettivo_Username;
	}
	public String getFascicolo_StatoEffettivo_TipoStatoFascicolo_Id() {
		return Fascicolo_StatoEffettivo_TipoStatoFascicolo_Id;
	}
	public void setFascicolo_StatoEffettivo_TipoStatoFascicolo_Id(String fascicolo_StatoEffettivo_TipoStatoFascicolo_Id) {
		Fascicolo_StatoEffettivo_TipoStatoFascicolo_Id = fascicolo_StatoEffettivo_TipoStatoFascicolo_Id;
	}
	public String getFascicolo_StatoEffettivo_DataSys() {
		return Fascicolo_StatoEffettivo_DataSys;
	}
	public void setFascicolo_StatoEffettivo_DataSys(String fascicolo_StatoEffettivo_DataSys) {
		Fascicolo_StatoEffettivo_DataSys = fascicolo_StatoEffettivo_DataSys;
	}
	public String getFascicolo_StatoEffettivo_UserId() {
		return Fascicolo_StatoEffettivo_UserId;
	}
	public void setFascicolo_StatoEffettivo_UserId(String fascicolo_StatoEffettivo_UserId) {
		Fascicolo_StatoEffettivo_UserId = fascicolo_StatoEffettivo_UserId;
	}
	public String getFascicolo_StatoEffettivo_Username() {
		return Fascicolo_StatoEffettivo_Username;
	}
	public void setFascicolo_StatoEffettivo_Username(String fascicolo_StatoEffettivo_Username) {
		Fascicolo_StatoEffettivo_Username = fascicolo_StatoEffettivo_Username;
	}
	
	@Override
	public String toString() {
		return "InpPlaceTfascicolo [address_from=" + address_from + ", Fascicolo_Id=" + Fascicolo_Id
				+ ", Fascicolo_Codice=" + Fascicolo_Codice + ", Fascicolo_Tipo=" + Fascicolo_Tipo + ", Fascicolo_Stato="
				+ Fascicolo_Stato + ", Fascicolo_Descrizione=" + Fascicolo_Descrizione + ", Fascicolo_Note="
				+ Fascicolo_Note + ", Fascicolo_GruppoFascicoli_Id=" + Fascicolo_GruppoFascicoli_Id
				+ ", Fascicolo_TipoFascicolo_Id=" + Fascicolo_TipoFascicolo_Id + ", Fascicolo_TipoPratica_Id="
				+ Fascicolo_TipoPratica_Id + ", Fascicolo_IdPratica=" + Fascicolo_IdPratica
				+ ", Fascicolo_CodiceSoggetto=" + Fascicolo_CodiceSoggetto + ", Fascicolo_IdentificativoEsterno="
				+ Fascicolo_IdentificativoEsterno + ", Fascicolo_CodiceFascicolo=" + Fascicolo_CodiceFascicolo
				+ ", Fascicolo_AziendaMittente_Id=" + Fascicolo_AziendaMittente_Id + ", Fascicolo_Ins_DataSys="
				+ Fascicolo_Ins_DataSys + ", Fascicolo_DataRif=" + Fascicolo_DataRif + ", Fascicolo_ECM_Node="
				+ Fascicolo_ECM_Node + ", Fascicolo_MetaData=" + Fascicolo_MetaData + ", Fascicolo_Fine_DataSys="
				+ Fascicolo_Fine_DataSys + ", Fascicolo_ScoreCalcolato=" + Fascicolo_ScoreCalcolato
				+ ", Fascicolo_ScoreManuale=" + Fascicolo_ScoreManuale + ", Fascicolo_ScoreCalcolato_DataSys="
				+ Fascicolo_ScoreCalcolato_DataSys + ", Fascicolo_ScoreManuale_DataSys="
				+ Fascicolo_ScoreManuale_DataSys + ", Fascicolo_ScoreManuale_UserId=" + Fascicolo_ScoreManuale_UserId
				+ ", Fascicolo_StatoCalcolato_TipoStatoFascicolo_Id=" + Fascicolo_StatoCalcolato_TipoStatoFascicolo_Id
				+ ", Fascicolo_StatoCalcolato_DataSys=" + Fascicolo_StatoCalcolato_DataSys
				+ ", Fascicolo_StatoManuale_TipoStatoFascicolo_Id=" + Fascicolo_StatoManuale_TipoStatoFascicolo_Id
				+ ", Fascicolo_StatoManuale_DataSys=" + Fascicolo_StatoManuale_DataSys
				+ ", Fascicolo_StatoManuale_Username=" + Fascicolo_StatoManuale_Username + ", Fascicolo_Valore="
				+ Fascicolo_Valore + ", Fascicolo_StatoManuale_UserId=" + Fascicolo_StatoManuale_UserId
				+ ", Fascicolo_ScoreCalcolato_UserId=" + Fascicolo_ScoreCalcolato_UserId
				+ ", Fascicolo_ScoreCalcolato_Username=" + Fascicolo_ScoreCalcolato_Username
				+ ", Fascicolo_StatoCalcolato_UserId=" + Fascicolo_StatoCalcolato_UserId
				+ ", Fascicolo_StatoCalcolato_Username=" + Fascicolo_StatoCalcolato_Username
				+ ", Fascicolo_FileGuida_Id=" + Fascicolo_FileGuida_Id + ", Fascicolo_RigaFileGuida="
				+ Fascicolo_RigaFileGuida + ", Fascicolo_ScoreEffettivo=" + Fascicolo_ScoreEffettivo
				+ ", Fascicolo_ScoreEffettivo_DataSys=" + Fascicolo_ScoreEffettivo_DataSys
				+ ", Fascicolo_ScoreEffettivo_UserId=" + Fascicolo_ScoreEffettivo_UserId
				+ ", Fascicolo_ScoreEffettivo_Username=" + Fascicolo_ScoreEffettivo_Username
				+ ", Fascicolo_StatoEffettivo_TipoStatoFascicolo_Id=" + Fascicolo_StatoEffettivo_TipoStatoFascicolo_Id
				+ ", Fascicolo_StatoEffettivo_DataSys=" + Fascicolo_StatoEffettivo_DataSys
				+ ", Fascicolo_StatoEffettivo_UserId=" + Fascicolo_StatoEffettivo_UserId
				+ ", Fascicolo_StatoEffettivo_Username=" + Fascicolo_StatoEffettivo_Username + ", getAddress_from()="
				+ getAddress_from() + ", getFascicolo_Id()=" + getFascicolo_Id() + ", getFascicolo_Codice()="
				+ getFascicolo_Codice() + ", getFascicolo_Tipo()=" + getFascicolo_Tipo() + ", getFascicolo_Stato()="
				+ getFascicolo_Stato() + ", getFascicolo_Descrizione()=" + getFascicolo_Descrizione()
				+ ", getFascicolo_Note()=" + getFascicolo_Note() + ", getFascicolo_GruppoFascicoli_Id()="
				+ getFascicolo_GruppoFascicoli_Id() + ", getFascicolo_TipoFascicolo_Id()="
				+ getFascicolo_TipoFascicolo_Id() + ", getFascicolo_TipoPratica_Id()=" + getFascicolo_TipoPratica_Id()
				+ ", getFascicolo_IdPratica()=" + getFascicolo_IdPratica() + ", getFascicolo_CodiceSoggetto()="
				+ getFascicolo_CodiceSoggetto() + ", getFascicolo_IdentificativoEsterno()="
				+ getFascicolo_IdentificativoEsterno() + ", getFascicolo_CodiceFascicolo()="
				+ getFascicolo_CodiceFascicolo() + ", getFascicolo_AziendaMittente_Id()="
				+ getFascicolo_AziendaMittente_Id() + ", getFascicolo_Ins_DataSys()=" + getFascicolo_Ins_DataSys()
				+ ", getFascicolo_DataRif()=" + getFascicolo_DataRif() + ", getFascicolo_ECM_Node()="
				+ getFascicolo_ECM_Node() + ", getFascicolo_MetaData()=" + getFascicolo_MetaData()
				+ ", getFascicolo_Fine_DataSys()=" + getFascicolo_Fine_DataSys() + ", getFascicolo_ScoreCalcolato()="
				+ getFascicolo_ScoreCalcolato() + ", getFascicolo_ScoreManuale()=" + getFascicolo_ScoreManuale()
				+ ", getFascicolo_ScoreCalcolato_DataSys()=" + getFascicolo_ScoreCalcolato_DataSys()
				+ ", getFascicolo_ScoreManuale_DataSys()=" + getFascicolo_ScoreManuale_DataSys()
				+ ", getFascicolo_ScoreManuale_UserId()=" + getFascicolo_ScoreManuale_UserId()
				+ ", getFascicolo_StatoCalcolato_TipoStatoFascicolo_Id()="
				+ getFascicolo_StatoCalcolato_TipoStatoFascicolo_Id() + ", getFascicolo_StatoCalcolato_DataSys()="
				+ getFascicolo_StatoCalcolato_DataSys() + ", getFascicolo_StatoManuale_TipoStatoFascicolo_Id()="
				+ getFascicolo_StatoManuale_TipoStatoFascicolo_Id() + ", getFascicolo_StatoManuale_DataSys()="
				+ getFascicolo_StatoManuale_DataSys() + ", getFascicolo_StatoManuale_Username()="
				+ getFascicolo_StatoManuale_Username() + ", getFascicolo_Valore()=" + getFascicolo_Valore()
				+ ", getFascicolo_StatoManuale_UserId()=" + getFascicolo_StatoManuale_UserId()
				+ ", getFascicolo_ScoreCalcolato_UserId()=" + getFascicolo_ScoreCalcolato_UserId()
				+ ", getFascicolo_ScoreCalcolato_Username()=" + getFascicolo_ScoreCalcolato_Username()
				+ ", getFascicolo_StatoCalcolato_UserId()=" + getFascicolo_StatoCalcolato_UserId()
				+ ", getFascicolo_StatoCalcolato_Username()=" + getFascicolo_StatoCalcolato_Username()
				+ ", getFascicolo_FileGuida_Id()=" + getFascicolo_FileGuida_Id() + ", getFascicolo_RigaFileGuida()="
				+ getFascicolo_RigaFileGuida() + ", getFascicolo_ScoreEffettivo()=" + getFascicolo_ScoreEffettivo()
				+ ", getFascicolo_ScoreEffettivo_DataSys()=" + getFascicolo_ScoreEffettivo_DataSys()
				+ ", getFascicolo_ScoreEffettivo_UserId()=" + getFascicolo_ScoreEffettivo_UserId()
				+ ", getFascicolo_ScoreEffettivo_Username()=" + getFascicolo_ScoreEffettivo_Username()
				+ ", getFascicolo_StatoEffettivo_TipoStatoFascicolo_Id()="
				+ getFascicolo_StatoEffettivo_TipoStatoFascicolo_Id() + ", getFascicolo_StatoEffettivo_DataSys()="
				+ getFascicolo_StatoEffettivo_DataSys() + ", getFascicolo_StatoEffettivo_UserId()="
				+ getFascicolo_StatoEffettivo_UserId() + ", getFascicolo_StatoEffettivo_Username()="
				+ getFascicolo_StatoEffettivo_Username() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}

	     