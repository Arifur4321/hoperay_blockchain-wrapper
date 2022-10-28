package com.vantea.hoperay.multichainwrapper.services.beans.input;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class IssueMoreBody {

	@NotNull
	@NotEmpty
	private String address_to;

	@NotNull
	@NotEmpty
	private String voucher_name;

	@NotNull
	@Min(value = 1)
	private int quantity;
	


	@NotNull
	@NotEmpty
	private String cliente_id;
	

	@NotNull
	@NotEmpty
    private String cliente_stato;
	 

	@NotNull
	@NotEmpty	
	private String cliente_codice;
	

	@NotNull
	@NotEmpty
	private String cliente_note;
	
	
	
	public String getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(String cliente_id) {
		this.cliente_id = cliente_id;
	}

	public String getCliente_stato() {
		return cliente_stato;
	}

	public void setCliente_stato(String cliente_stato) {
		this.cliente_stato = cliente_stato;
	}

	public String getCliente_codice() {
		return cliente_codice;
	}

	public void setCliente_codice(String cliente_codice) {
		this.cliente_codice = cliente_codice;
	}

	public String getCliente_note() {
		return cliente_note;
	}

	public void setCliente_note(String cliente_note) {
		this.cliente_note = cliente_note;
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

	public void setVoucher_name(String voucher) {
		this.voucher_name = voucher;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "IssueMoreBody [address_to=" + address_to + ", voucher_name=" + voucher_name + ", quantity=" + quantity
				+ ", cliente_id=" + cliente_id + ", cliente_stato=" + cliente_stato + ", cliente_codice="
				+ cliente_codice + ", cliente_note=" + cliente_note + "]";
	}

}
