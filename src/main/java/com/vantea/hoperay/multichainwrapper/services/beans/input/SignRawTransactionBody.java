package com.vantea.hoperay.multichainwrapper.services.beans.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SignRawTransactionBody {

	@NotNull
	@NotEmpty
	private String rawtx;
	
	@NotNull
	@NotEmpty
	private String private_key;

	public String getRawtx() {
		return rawtx;
	}

	public void setRawtx(String rawtx) {
		this.rawtx = rawtx;
	}

	public String getPrivate_key() {
		return private_key;
	}

	public void setPrivate_key(String private_key) {
		this.private_key = private_key;
	}

	@Override
	public String toString() {
		return "SignRawTransactionBody [rawtx=" + rawtx + ", private_key=*****]";
	}

}
