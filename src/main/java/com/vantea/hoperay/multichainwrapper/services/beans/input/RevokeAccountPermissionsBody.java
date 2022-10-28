package com.vantea.hoperay.multichainwrapper.services.beans.input;

public class RevokeAccountPermissionsBody {

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "RevokeAccountPermissionsBody [address=" + address + "]";
	}

}
