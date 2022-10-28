package com.vantea.hoperay.multichainwrapper.services.beans.input;

import java.util.Arrays;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GetTransactionsBody {

	@NotNull
	@NotEmpty
	private String address;

	//@NotNull
	private String[] assets;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String[] getAssets() {
		return assets;
	}

	public void setAssets(String[] assets) {
		this.assets = assets;
	}

	@Override
	public String toString() {
		return "GetTransactionsBody [address=" + address + ", assets=" + Arrays.toString(assets) + "]";
	}

}
