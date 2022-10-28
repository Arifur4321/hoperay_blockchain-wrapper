package com.vantea.hoperay.multichainwrapper.services.beans.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BurnVouchersBody {

	@NotNull
	@NotEmpty
	private String address_from;

	@NotNull
	@NotEmpty
	@Valid
	private List<VoucherQuantity> transfer;

	public String getAddress_from() {
		return address_from;
	}

	public void setAddress_from(String address_from) {
		this.address_from = address_from;
	}

	public List<VoucherQuantity> getTransfer() {
		return transfer;
	}

	public void setTransfer(List<VoucherQuantity> transfer) {
		this.transfer = transfer;
	}

	@Override
	public String toString() {
		return "BurnVouchersBody [address_from=" + address_from + ", transfer=" + transfer + "]";
	}

}
