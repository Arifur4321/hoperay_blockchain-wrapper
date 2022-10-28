package com.vantea.hoperay.multichainwrapper.services.beans.input;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class IssueNewVoucherBody {
	
	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	private int value;

	@NotNull
	@Future
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiration;

	@NotNull
	@NotEmpty
	private String address_to;

	@NotNull
	@Min(value = 1)
	private int quantity;

    
	private String userName ;
	private String idFascicolo;
	private String statoOld;
	private String statoNew;
	

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public LocalDate getExpiration() {
		return expiration;
	}
	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}
	public String getAddress_to() {
		return address_to;
	}
	public void setAddress_to(String address_to) {
		this.address_to = address_to;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	@Override
	public String toString() {
		return "IssueNewVoucherBody [name=" + name + ", value=" + value + ", expiration=" + expiration + ", address_to="
				+ address_to + ", quantity=" + quantity + ", userName=" + userName + ", idFascicolo=" + idFascicolo
				+ ", statoOld=" + statoOld + ", statoNew=" + statoNew + "]";
	}
		
}
