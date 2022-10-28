/**
 * Class mapping voucher and quantity for transfer
 */
package com.vantea.hoperay.multichainwrapper.services.beans.input;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VoucherQuantity {

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	@Min(value = 1)
	private long qty;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "VoucherQuantity [name=" + name + ", qty=" + qty + "]";
	}

}
