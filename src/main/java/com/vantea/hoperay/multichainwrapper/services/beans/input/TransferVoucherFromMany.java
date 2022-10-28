/**
 * Class mapping body for "transfer_voucher_from_many" request
 */
package com.vantea.hoperay.multichainwrapper.services.beans.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TransferVoucherFromMany {

	@NotNull
	@NotEmpty
	private String address_from;

	@NotNull
	@NotEmpty
	@Valid
	private List<Destination> destinations;

	public String getAddress_from() {
		return address_from;
	}

	public void setAddress_from(String address_from) {
		this.address_from = address_from;
	}

	public List<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(List<Destination> destinations) {
		this.destinations = destinations;
	}

	@Override
	public String toString() {
		return "TransferVoucherFromMany [address_from=" + address_from + ", destinations=" + destinations + "]";
	}

}
