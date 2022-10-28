package com.vantea.hoperay.multichainwrapper.services.beans.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BroadcastRawTxBody {

	@NotNull
	@NotEmpty
	private String rawtx;

	public String getRawtx() {
		return rawtx;
	}

	public void setRawtx(String rawtx) {
		this.rawtx = rawtx;
	}

	@Override
	public String toString() {
		return "BroadcastRawTxBody [rawtx=" + rawtx + "]";
	}

}
