package com.vantea.hoperay.multichainwrapper.services.beans.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GrantAccountPermissionsBody {
	
	@NotNull
	@NotEmpty
	private String address;
	
	@NotNull
	private Roles role;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "GrantAccountPermissionsBody [address=" + address + ", role=" + role + "]";
	}

}
