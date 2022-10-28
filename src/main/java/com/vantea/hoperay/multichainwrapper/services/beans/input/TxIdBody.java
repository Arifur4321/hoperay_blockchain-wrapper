package com.vantea.hoperay.multichainwrapper.services.beans.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TxIdBody {

	@NotNull
	@NotEmpty
	private String txid;

	public String getTxid() {
		return txid;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	@Override
	public String toString() {
		return "TxIdBody [txid=" + txid + "]";
	}

}
