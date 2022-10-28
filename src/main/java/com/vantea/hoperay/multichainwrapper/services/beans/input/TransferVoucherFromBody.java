/**
 * Class mapping body for Transer Voucher Request
 */
package com.vantea.hoperay.multichainwrapper.services.beans.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TransferVoucherFromBody {

	@NotNull
	@NotEmpty
	private String address_from;

	@NotNull
	@NotEmpty
	private String address_to;

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

	public String getAddress_to() {
		return address_to;
	}

	public void setAddress_to(String address_to) {
		this.address_to = address_to;
	}

	public List<VoucherQuantity> getTransfer() {
		return transfer;
	}

	public void setTransfer(List<VoucherQuantity> transfer) {
		this.transfer = transfer;
	}

	@Override
	public String toString() {
		return "TransferVoucherFromBody [address_from=" + address_from + ", address_to=" + address_to + ", transfer="
				+ transfer + "]";
	}

}
