/**
 * Body object from "get_voucher_balances" API
 */
package com.vantea.hoperay.multichainwrapper.services.beans.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GetVoucherBalancesBody {

	@NotNull
	@NotEmpty
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "GetVoucherBalancesBody [address=" + address + "]";
	}

}
