package com.vantea.hoperay.multichainwrapper.services.beans;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InpPlaceTcliente {
	
	@NotNull
	@NotEmpty
	private String address_to;


	@NotNull
	@NotEmpty
	private String voucher_name;

	@NotNull
	@Min(value = 1)
	private int quantity;


	public String Cliente_Id;
    public String Cliente_Stato;
	public String Cliente_Codice;
	public String Cliente_Note;
	/*
	public String Cliente_RagioneSociale;
	public String Cliente_DataContratto;
	public String Cliente_Indirizzo;
	public String Cliente_CAP;
	public String Cliente_Localita;
	public String Cliente_Provincia;
	public String Cliente_Contatto;
	public String Cliente_Email;
	*/
	

	
	public String getCliente_Id() {
		return Cliente_Id;
	}
	public void setCliente_Id(String cliente_Id) {
		Cliente_Id = cliente_Id;
	}
	public String getCliente_Stato() {
		return Cliente_Stato;
	}
	public void setCliente_Stato(String cliente_Stato) {
		Cliente_Stato = cliente_Stato;
	}
	public String getCliente_Codice() {
		return Cliente_Codice;
	}
	public void setCliente_Codice(String cliente_Codice) {
		Cliente_Codice = cliente_Codice;
	}
	public String getCliente_Note() {
		return Cliente_Note;
	}
	public void setCliente_Note(String cliente_Note) {
		Cliente_Note = cliente_Note;
	}
	
	public String getAddress_to() {
		return address_to;
	}
	public void setAddress_to(String address_to) {
		this.address_to = address_to;
	}
	public String getVoucher_name() {
		return voucher_name;
	}
	public void setVoucher_name(String voucher_name) {
		this.voucher_name = voucher_name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "InpPlaceTcliente [address_to=" + address_to + ", voucher_name=" + voucher_name + ", quantity="
				+ quantity + ", Cliente_Id=" + Cliente_Id + ", Cliente_Stato=" + Cliente_Stato + ", Cliente_Codice="
				+ Cliente_Codice + ", Cliente_Note=" + Cliente_Note + ", getCliente_Id()=" + getCliente_Id()
				+ ", getCliente_Stato()=" + getCliente_Stato() + ", getCliente_Codice()=" + getCliente_Codice()
				+ ", getCliente_Note()=" + getCliente_Note() + ", getAddress_to()=" + getAddress_to()
				+ ", getVoucher_name()=" + getVoucher_name() + ", getQuantity()=" + getQuantity() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}